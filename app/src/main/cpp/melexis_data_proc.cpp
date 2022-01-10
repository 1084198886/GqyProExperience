#include<android/log.h>
#include <jni.h>
#include <string>
#include "mlx.h"
#include "malloc.h"

#define TAG "MLX-Jni" // 这个是自定义的LOG的标识
#define LOGD(...) __android_log_print(ANDROID_LOG_DEBUG,TAG ,__VA_ARGS__) // 定义LOGD类型
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO,TAG ,__VA_ARGS__) // 定义LOGI类型
#define LOGW(...) __android_log_print(ANDROID_LOG_WARN,TAG ,__VA_ARGS__) // 定义LOGW类型
#define LOGE(...) __android_log_print(ANDROID_LOG_ERROR,TAG ,__VA_ARGS__) // 定义LOGE类型
#define LOGF(...) __android_log_print(ANDROID_LOG_FATAL,TAG ,__VA_ARGS__) // 定义LOGF类型

static paramsMLX90640 s_param;


extern "C"
JNIEXPORT jboolean JNICALL
Java_com_newcapec_lib_melexis_NcJni_InitParameters(JNIEnv *env, jclass clazz,
                                                   jbyteArray init_data) {
    char *raw_data = (char *) env->GetByteArrayElements(init_data, NULL);

    if (env->GetArrayLength(init_data) < 1664) {
        return false;
    }
    LOGW( "Unable to retrieve EGL config" );
    LOGD("..%s..%d..DataLen=%d", __FUNCTION__, __LINE__, env->GetArrayLength(init_data));
    //填写到数据结构中去 FixMe
    MLX90640_ExtractParameters((uint16_t *) raw_data, &s_param);
    //s_param.kVdd = raw_data[0]<<8 + raw_data[1];

    //s_param.alpha =
    env->ReleaseByteArrayElements(init_data, (jbyte *) raw_data, 0);
    return true;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_newcapec_lib_melexis_NcJni_Calculate(JNIEnv *env, jclass clazz, jbyteArray org_data,
                                              jfloat emissivity, jfloat tr,
                                              jfloatArray result_data) {
    int org_len, cnt;
    char *raw_data, *raw_buf;
    float *out_data, *out_buf;

    float mlx90640_vdd, mlx90640_Ta;

    org_len = env->GetArrayLength(org_data);

    if (org_len < 1668) {
        return false;
    }
    raw_data = (char *) env->GetByteArrayElements(org_data, NULL);
    out_data = (float *) env->GetFloatArrayElements(result_data, NULL);

    //LOGD("..%s..%d..DataLen=%d", __FUNCTION__, __LINE__, org_len);

    raw_buf = (char *) malloc(org_len);
    if (raw_buf == NULL) {
        LOGD(">>> ..%s..%d..buf_in == NULL ..exit", __FUNCTION__, __LINE__);
        return false;
    }

    out_buf = (float *) malloc(org_len * sizeof(float));
    if (out_buf == NULL) {
        LOGD(">>> ..%s..%d..buf_out == NULL ..exit", __FUNCTION__, __LINE__);
        return false;
    }

    for (cnt = 0; cnt < org_len; cnt++) {
        raw_buf[cnt] = raw_data[cnt];
    }

    //FixMe ...
    {
        mlx90640_vdd = MLX90640_GetVdd((uint16_t *) raw_buf, &s_param);//计算VDD
        mlx90640_Ta = MLX90640_GetTa((uint16_t *) raw_buf, &s_param);//计算实时外壳温度
        tr = mlx90640_Ta - 8;//TA_SHIFT; 	//计算环境温度用于温度补偿
        //LOGD("Calculate:%.2f,%.2f,%.2f", mlx90640_Ta, mlx90640_vdd, tr);
        MLX90640_CalculateTo((uint16_t *) raw_buf, &s_param, emissivity, tr,
                             out_buf);//reinterpret_cast<uint16_t *>
    }
    out_buf[768] = mlx90640_Ta;
    out_buf[769] = tr;
    out_buf[770] = mlx90640_vdd;
    for (cnt = 0; cnt < org_len; cnt++) {
        out_data[cnt] = out_buf[cnt];
    }

    free(raw_buf);
    free(out_buf);
    if (org_data && raw_data) {
        env->ReleaseByteArrayElements(org_data, (jbyte *) raw_data, 0);
    }
    if (result_data && out_data) {
        env->ReleaseFloatArrayElements(result_data, (jfloat *) out_data, 0);
    }
    return true;
}

extern "C"
JNIEXPORT jboolean JNICALL
Java_com_newcapec_lib_melexis_NcJni_CalculateInteger(JNIEnv *env, jclass clazz,
                                                     jbyteArray org_data, jfloat emissivity,
                                                     jfloat tr, jintArray result_data) {
    int org_len, cnt;
    char *raw_data, *raw_buf;
    int *out_data;
    float *out_buf;
    int mode=0;
    static float ta_pre=0;//


    float mlx90640_vdd, mlx90640_Ta;

    org_len = env->GetArrayLength(org_data);

    if (org_len < 1668) {
        return false;
    }
    raw_data = (char *) env->GetByteArrayElements(org_data, NULL);
    out_data = (int *) env->GetIntArrayElements(result_data, NULL);
    //LOGD("..%s..%d..DataLen=%d", __FUNCTION__, __LINE__, org_len);

    raw_buf = (char *) malloc(org_len);
    if (raw_buf == NULL) {
        LOGD(">>> ..%s..%d..buf_in == NULL ..exit", __FUNCTION__, __LINE__);
        return false;
    }

    out_buf = (float *) malloc(org_len * sizeof(float));
    if (out_buf == NULL) {
        LOGD(">>> ..%s..%d..buf_out == NULL ..exit", __FUNCTION__, __LINE__);
        return false;
    }

    for (cnt = 0; cnt < org_len; cnt++) {
        raw_buf[cnt] = raw_data[cnt];
    }
    mlx90640_vdd = MLX90640_GetVdd((uint16_t *) raw_buf, &s_param);//计算VDD
    mlx90640_Ta = MLX90640_GetTa((uint16_t *) raw_buf, &s_param);//计算实时外壳温度
    if(tr <= -100.0){
        if((mlx90640_Ta < -5300) || (mlx90640_Ta > 5300)){
            mlx90640_Ta = ta_pre;
        }
        else{
            if((mlx90640_Ta > -5300) || (mlx90640_Ta < 5300))
                ta_pre = mlx90640_Ta;
        }
        tr = mlx90640_Ta - 8;   //TA_SHIFT; 	//计算环境温度用于温度补偿
    }

    //LOGD("Calculate:%.2f,%.2f,%.2f", mlx90640_Ta, mlx90640_vdd, tr);
    raw_buf[833*2] = 1;//转换第1帧数据
    raw_buf[833*2+1] = 0;
    MLX90640_CalculateTo((uint16_t *) raw_buf, &s_param, emissivity, tr,
                         out_buf);//reinterpret_cast<uint16_t *>
    raw_buf[833*2] = 0;//转换第0帧数据
    raw_buf[833*2+1] = 0;
    MLX90640_CalculateTo((uint16_t *) raw_buf, &s_param, emissivity, tr,
     out_buf);
    //坏点处理
    mode = (*(uint16_t*)&raw_buf[832*2] & 0x1000) >> 12;
    MLX90640_BadPixelsCorrection((&s_param)->brokenPixels, out_buf, mode, &s_param);
    MLX90640_BadPixelsCorrection((&s_param)->outlierPixels, out_buf, mode, &s_param);
    out_buf[768] = mlx90640_Ta;
    out_buf[769] = tr;
    out_buf[770] = mlx90640_vdd;
    for (cnt = 0; cnt < org_len; cnt++) {
        out_data[cnt] = (int) (out_buf[cnt] * 100);
    }

    free(raw_buf);
    free(out_buf);
    if (org_data && raw_data) {
        env->ReleaseByteArrayElements(org_data, (jbyte *) raw_data, 0);
    }
    if (result_data && out_data) {
        env->ReleaseIntArrayElements(result_data, (jint *) out_data, 0);
    }
    return true;
}
