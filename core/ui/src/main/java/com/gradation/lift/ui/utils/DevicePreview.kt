package com.gradation.lift.ui.utils

import androidx.compose.ui.tooling.preview.Devices.PIXEL_3
import androidx.compose.ui.tooling.preview.Devices.PIXEL_4_XL
import androidx.compose.ui.tooling.preview.Preview

@Preview(name = "SAMSUNG Galaxy S20", device = "spec:shape=Normal,width=360,height=800,unit=dp,dpi=563")
@Preview(name = "SAMSUNG Galaxy S10", device = "spec:shape=Normal,width=360,height=740,unit=dp,dpi=360")
@Preview(name = "PIXEL_3", device = PIXEL_3)
//@Preview(name = "PIXEL_4_XL", device = PIXEL_4_XL)
annotation class DevicePreview