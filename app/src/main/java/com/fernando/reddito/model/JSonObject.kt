package com.fernando.reddito.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

class JSonObject {

    @SerializedName("kind")
    @Expose
    var kind: String? = null
    @SerializedName("data")
    @Expose
    var data: Data? = null

    fun withKind(kind: String): JSonObject {
        this.kind = kind
        return this
    }

    fun withData(data: Data): JSonObject {
        this.data = data
        return this
    }

    override fun toString(): String {
        return ToStringBuilder(this).append("kind", kind).append("data", data).toString()
    }

}
