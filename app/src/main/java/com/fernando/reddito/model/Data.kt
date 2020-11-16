package com.fernando.reddito.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

class Data {

    @SerializedName("modhash")
    @Expose
    var modhash: String? = null
    @SerializedName("dist")
    @Expose
    var dist: Int? = null
    @SerializedName("children")
    @Expose
    var children: List<Child>? = null

    fun withModhash(modhash: String): Data {
        this.modhash = modhash
        return this
    }

    fun withDist(dist: Int?): Data {
        this.dist = dist
        return this
    }

    fun withChildren(children: List<Child>): Data {
        this.children = children
        return this
    }

    override fun toString(): String {
        return ToStringBuilder(this).append("modhash", modhash).append("dist", dist)
            .append("children", children).toString()
    }

}
