package com.vastausf.wesolient.data.common

import java.util.*

open class Scope {
    var uid: String = UUID.randomUUID().toString()

    var title: String = ""

    var url: String = ""

    var composes: List<Compose> = emptyList()
}
