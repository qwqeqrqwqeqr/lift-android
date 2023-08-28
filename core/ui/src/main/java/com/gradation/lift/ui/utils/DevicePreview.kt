package com.gradation.lift.ui.utils

import androidx.compose.ui.tooling.preview.Devices.PIXEL_3
import androidx.compose.ui.tooling.preview.Preview

/**
 * [DevicePreview]
 * Preview Test를 위해 지정해놓은 템플릿
 * @since 2023-08-28 22:39:20
 */
@Preview(name = "SAMSUNG Galaxy S20", device = "spec:shape=Normal,width=360,height=800,unit=dp,dpi=563")
@Preview(name = "SAMSUNG Galaxy S10", device = "spec:shape=Normal,width=360,height=740,unit=dp,dpi=360")
@Preview(name = "PIXEL_3", device = PIXEL_3)
annotation class DevicePreview