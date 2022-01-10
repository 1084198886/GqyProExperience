//
// Created by 谢琦 on 2020/8/13.
//

#ifndef _MLX_H
#define _MLX_H

#define SCALEALPHA 0.000001f

typedef struct {
    int16_t kVdd;
    int16_t vdd25;
    float KvPTAT;
    float KtPTAT;
    uint16_t vPTAT25;
    float alphaPTAT;
    int16_t gainEE;
    float tgc;
    float cpKv;
    float cpKta;
    uint8_t resolutionEE;
    uint8_t calibrationModeEE;
    float KsTa;
    float ksTo[5];
    int16_t ct[5];
    uint16_t alpha[768];
    uint8_t alphaScale;
    int16_t offset[768];
    int8_t kta[768];
    uint8_t ktaScale;
    int8_t kv[768];
    uint8_t kvScale;
    float cpAlpha[2];
    int16_t cpOffset[2];
    float ilChessC[3];
    uint16_t brokenPixels[5];
    uint16_t outlierPixels[5];
} paramsMLX90640;

#ifdef __cplusplus             //告诉编译器，这部分代码按C语言的格式进行编译，而不是C++的
extern "C"{
#endif

int MLX90640_ExtractParameters(uint16_t *eeData, paramsMLX90640 *mlx90640);

float MLX90640_GetVdd(uint16_t *frameData, const paramsMLX90640 *params);

float MLX90640_GetTa(uint16_t *frameData, const paramsMLX90640 *params);

void MLX90640_GetImage(uint16_t *frameData, const paramsMLX90640 *params, float *result);

void MLX90640_CalculateTo(uint16_t *frameData, const paramsMLX90640 *params, float emissivity, float tr,
                          float *result);

int MLX90640_GetSubPageNumber(uint16_t *frameData);

int MLX90640_SetInterleavedMode(uint8_t slaveAddr);

int MLX90640_SetChessMode(uint8_t slaveAddr);

void MLX90640_BadPixelsCorrection(uint16_t *pixels, float *to, int mode, paramsMLX90640 *params);

#ifdef __cplusplus
}
#endif

#endif //_MLX_H
