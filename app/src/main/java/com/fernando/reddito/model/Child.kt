package com.fernando.reddito.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder
import java.io.Serializable

class Child: Serializable {

    @SerializedName("kind")
    @Expose
    var kind: String? = null
    @SerializedName("data")
    @Expose
    var dataChild: DataChild? = null

    fun withKind(kind: String): Child {
        this.kind = kind
        return this
    }

    fun withData(dataChild: DataChild): Child {
        this.dataChild = dataChild
        return this
    }

    override fun toString(): String {
        return ToStringBuilder(this).append("kind", kind).append("dataChild", dataChild).toString()
    }

}
