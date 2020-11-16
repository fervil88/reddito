package com.fernando.reddito.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import org.apache.commons.lang3.builder.ToStringBuilder

class DataChild {

    @SerializedName("subreddit")
    @Expose
    var subreddit: String? = null
    @SerializedName("selftext")
    @Expose
    var selftext: String? = null
    @SerializedName("author_fullname")
    @Expose
    var authorFullname: String? = null
    @SerializedName("saved")
    @Expose
    var saved: Boolean? = null
    @SerializedName("mod_reason_title")
    @Expose
    var modReasonTitle: Any? = null
    @SerializedName("clicked")
    @Expose
    var clicked: Boolean? = null
    @SerializedName("title")
    @Expose
    var title: String? = null
    @SerializedName("link_flair_richtext")
    @Expose
    var linkFlairRichtext: List<Any>? = null
    @SerializedName("subreddit_name_prefixed")
    @Expose
    var subredditNamePrefixed: String? = null
    @SerializedName("hidden")
    @Expose
    var hidden: Boolean? = null
    @SerializedName("thumbnail_height")
    @Expose
    var thumbnailHeight: Int? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("ups")
    @Expose
    var ups: Int? = null
    @SerializedName("thumbnail_width")
    @Expose
    var thumbnailWidth: Int? = null
    @SerializedName("score")
    @Expose
    var score: Int? = null
    @SerializedName("thumbnail")
    @Expose
    var thumbnail: String? = null
    @SerializedName("post_hint")
    @Expose
    var postHint: String? = null
    @SerializedName("created")
    @Expose
    var created: Double? = null
    @SerializedName("domain")
    @Expose
    var domain: String? = null
    @SerializedName("allow_live_comments")
    @Expose
    var allowLiveComments: Boolean? = null
    @SerializedName("url_overridden_by_dest")
    @Expose
    var urlOverriddenByDest: String? = null
    @SerializedName("media_only")
    @Expose
    var mediaOnly: Boolean? = null
    @SerializedName("can_gild")
    @Expose
    var canGild: Boolean? = null
    @SerializedName("visited")
    @Expose
    var visited: Boolean? = null
    @SerializedName("subreddit_id")
    @Expose
    var subredditId: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("num_comments")
    @Expose
    var numComments: Int? = null
    @SerializedName("permalink")
    @Expose
    var permalink: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
    @SerializedName("created_utc")
    @Expose
    var createdUtc: Double? = null
    @SerializedName("is_video")
    @Expose
    var isVideo: Boolean? = null

    fun withSubreddit(subreddit: String): DataChild {
        this.subreddit = subreddit
        return this
    }

    fun withSelftext(selftext: String): DataChild {
        this.selftext = selftext
        return this
    }

    fun withAuthorFullname(authorFullname: String): DataChild {
        this.authorFullname = authorFullname
        return this
    }

    fun withSaved(saved: Boolean?): DataChild {
        this.saved = saved
        return this
    }

    fun withModReasonTitle(modReasonTitle: Any): DataChild {
        this.modReasonTitle = modReasonTitle
        return this
    }

    fun withClicked(clicked: Boolean?): DataChild {
        this.clicked = clicked
        return this
    }

    fun withTitle(title: String): DataChild {
        this.title = title
        return this
    }

    fun withLinkFlairRichtext(linkFlairRichtext: List<Any>): DataChild {
        this.linkFlairRichtext = linkFlairRichtext
        return this
    }

    fun withSubredditNamePrefixed(subredditNamePrefixed: String): DataChild {
        this.subredditNamePrefixed = subredditNamePrefixed
        return this
    }

    fun withHidden(hidden: Boolean?): DataChild {
        this.hidden = hidden
        return this
    }

    fun withThumbnailHeight(thumbnailHeight: Int?): DataChild {
        this.thumbnailHeight = thumbnailHeight
        return this
    }

    fun withName(name: String): DataChild {
        this.name = name
        return this
    }

    fun withUps(ups: Int?): DataChild {
        this.ups = ups
        return this
    }

    fun withThumbnailWidth(thumbnailWidth: Int?): DataChild {
        this.thumbnailWidth = thumbnailWidth
        return this
    }

    fun withScore(score: Int?): DataChild {
        this.score = score
        return this
    }

    fun withThumbnail(thumbnail: String): DataChild {
        this.thumbnail = thumbnail
        return this
    }

    fun withPostHint(postHint: String): DataChild {
        this.postHint = postHint
        return this
    }

    fun withCreated(created: Double?): DataChild {
        this.created = created
        return this
    }

    fun withDomain(domain: String): DataChild {
        this.domain = domain
        return this
    }

    fun withAllowLiveComments(allowLiveComments: Boolean?): DataChild {
        this.allowLiveComments = allowLiveComments
        return this
    }

    fun withUrlOverriddenByDest(urlOverriddenByDest: String): DataChild {
        this.urlOverriddenByDest = urlOverriddenByDest
        return this
    }

    fun withMediaOnly(mediaOnly: Boolean?): DataChild {
        this.mediaOnly = mediaOnly
        return this
    }

    fun withCanGild(canGild: Boolean?): DataChild {
        this.canGild = canGild
        return this
    }

    fun withVisited(visited: Boolean?): DataChild {
        this.visited = visited
        return this
    }

    fun withSubredditId(subredditId: String): DataChild {
        this.subredditId = subredditId
        return this
    }

    fun withId(id: String): DataChild {
        this.id = id
        return this
    }

    fun withAuthor(author: String): DataChild {
        this.author = author
        return this
    }

    fun withNumComments(numComments: Int?): DataChild {
        this.numComments = numComments
        return this
    }

    fun withPermalink(permalink: String): DataChild {
        this.permalink = permalink
        return this
    }

    fun withUrl(url: String): DataChild {
        this.url = url
        return this
    }

    fun withCreatedUtc(createdUtc: Double?): DataChild {
        this.createdUtc = createdUtc
        return this
    }

    fun withIsVideo(isVideo: Boolean?): DataChild {
        this.isVideo = isVideo
        return this
    }

    override fun toString(): String {
        return ToStringBuilder(this)
            .append("subreddit", subreddit).append("selftext", selftext)
            .append("authorFullname", authorFullname).append("saved", saved)
            .append("modReasonTitle", modReasonTitle).append("clicked", clicked)
            .append("title", title).append("linkFlairRichtext", linkFlairRichtext)
            .append("subredditNamePrefixed", subredditNamePrefixed).append("hidden", hidden)
            .append("thumbnailHeight", thumbnailHeight).append("name", name).append("ups", ups)
            .append("thumbnailWidth", thumbnailWidth).append("score", score)
            .append("thumbnail", thumbnail).append("postHint", postHint).append("created", created)
            .append("domain", domain).append("allowLiveComments", allowLiveComments)
            .append("urlOverriddenByDest", urlOverriddenByDest)
            .append("mediaOnly", mediaOnly)
            .append("canGild", canGild).append("visited", visited)
            .append("subredditId", subredditId).append("id", id).append("author", author)
            .append("numComments", numComments).append("permalink", permalink).append("url", url)
            .append("createdUtc", createdUtc)
            .append("isVideo", isVideo).toString()
    }

}
