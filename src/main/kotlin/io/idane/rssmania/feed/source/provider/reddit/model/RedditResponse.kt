package io.idane.rssmania.feed.source.provider.reddit.model

import com.google.gson.annotations.SerializedName

data class RedditResponse(
        var kind: String,
        var data: RedditData
)

data class RedditData(
        var children: MutableList<RedditChild> = mutableListOf()
)

data class RedditChild(
        var kind: String,
        var data: RedditPost
)

data class RedditPost(
        @SerializedName("approved_at_utc") val approvedAtUtc: String,
        @SerializedName("subreddit") val subreddit: String,
        @SerializedName("selftext") val selfText: String,
        @SerializedName("author_fullname") val authorFullname: String,
        @SerializedName("saved") val saved: Boolean,
        @SerializedName("mod_reason_title") val modReasonTitle: String,
        @SerializedName("gilded") val gilded: Int,
        @SerializedName("clicked") val clicked: Boolean,
        @SerializedName("title") val title: String,
//        @SerializedName("link_flair_richtext") val linkFlairRichtext: List<String>,
        @SerializedName("subreddit_name_prefixed") val subredditNamePrefixed: String,
        @SerializedName("hidden") val hidden: Boolean,
        @SerializedName("pwls") val pwls: Int,
        @SerializedName("link_flair_css_class") val linkFlairCssClass: String,
        @SerializedName("downs") val downs: Int,
        @SerializedName("top_awarded_type") val topAwardedType: String,
        @SerializedName("hide_score") val hide_score: Boolean,
        @SerializedName("name") val name: String,
        @SerializedName("quarantine") val quarantine: Boolean,
        @SerializedName("link_flair_text_color") val linkFlairTextColor: String,
        @SerializedName("upvote_ratio") val upvoteRatio: Double,
        @SerializedName("author_flair_background_color") val authorFlairBackgroundColor: String,
        @SerializedName("subreddit_type") val subredditType: String,
        @SerializedName("ups") val ups: Int,
        @SerializedName("total_awards_received") val totalAwardsReceived: Int,
        @SerializedName("media_embed") val mediaEmbed: Map<Any, Any?>,
        @SerializedName("author_flair_template_id") val authorFlairTemplateId: String,
        @SerializedName("is_original_content") val isOriginalContent: Boolean,
        @SerializedName("user_reports") val userReports: List<String>,
        @SerializedName("secure_media") val secureMedia: String,
        @SerializedName("is_reddit_media_domain") val isRedditMediaDomain: Boolean,
        @SerializedName("is_meta") val isMeta: Boolean,
        @SerializedName("category") val category: String,
        @SerializedName("secure_media_embed") val secureMediaEmbed: Map<String, Any?>,
        @SerializedName("link_flair_text") val linkFlairText: String,
        @SerializedName("can_mod_post") val canModPost: Boolean,
        @SerializedName("score") val score: Int,
        @SerializedName("approved_by") val approvedBy: String,
        @SerializedName("author_premium") val authorPremium: Boolean,
        @SerializedName("thumbnail") val thumbnail: String,
        @SerializedName("edited") val edited: Boolean,
        @SerializedName("author_flair_css_class") val authorFlairCssClass: String,
//        @SerializedName("author_flair_richtext") val authorFlairRichtext: List<String>,
        @SerializedName("gildings") val gildings: Map<String, Any?>,
        @SerializedName("content_categories") val contentCategories: String,
        @SerializedName("is_self") val isSelf: Boolean,
        @SerializedName("mod_note") val mod_note: String,
        @SerializedName("created") val created: Int,
        @SerializedName("link_flair_type") val linkFlairType: String,
        @SerializedName("wls") val wls: Int,
        @SerializedName("removed_by_category") val removedByCategory: String,
        @SerializedName("banned_by") val bannedBy: String,
        @SerializedName("author_flair_type") val authorFlairType: String,
        @SerializedName("domain") val domain: String,
        @SerializedName("allow_live_comments") val allowLiveComments: Boolean,
        @SerializedName("selftext_html") val selftextHtml: String,
        @SerializedName("likes") val likes: String,
        @SerializedName("suggested_sort") val suggestedSort: String,
        @SerializedName("banned_at_utc") val bannedAtUtc: String,
        @SerializedName("url_overridden_by_dest") val urlOverriddenByDest: String,
        @SerializedName("view_count") val viewCount: String,
        @SerializedName("archived") val archived: Boolean,
        @SerializedName("no_follow") val noFollow: Boolean,
        @SerializedName("is_crosspostable") val isCrosspostable: Boolean,
        @SerializedName("pinned") val pinned: Boolean,
        @SerializedName("over_18") val over_18: Boolean,
        @SerializedName("all_awardings") val allAwardings: Any,
        @SerializedName("awarders") val awarders: List<String>,
        @SerializedName("media_only") val mediaOnly: Boolean,
        @SerializedName("can_gild") val canGild: Boolean,
        @SerializedName("spoiler") val spoiler: Boolean,
        @SerializedName("locked") val locked: Boolean,
        @SerializedName("author_flair_text") val authorFlairText: String,
        @SerializedName("treatment_tags") val treatment_tags: List<String>,
        @SerializedName("visited") val visited: Boolean,
        @SerializedName("removed_by") val removedBy: String,
        @SerializedName("num_reports") val numReports: String,
        @SerializedName("distinguished") val distinguished: String,
        @SerializedName("subreddit_id") val subredditId: String,
        @SerializedName("mod_reason_by") val modReasonBy: String,
        @SerializedName("removal_reason") val removalReason: String,
        @SerializedName("link_flair_background_color") val linkFlairBackgroundColor: String,
        @SerializedName("id") val id: String,
        @SerializedName("is_robot_indexable") val isRobotIndexable: Boolean,
        @SerializedName("report_reasons") val reportReasons: String,
        @SerializedName("author") val author: String,
        @SerializedName("discussion_type") val discussionType: String,
        @SerializedName("num_comments") val numComments: Int,
        @SerializedName("send_replies") val sendReplies: Boolean,
        @SerializedName("whitelist_status") val whitelistStatus: String,
        @SerializedName("contest_mode") val contestMode: Boolean,
        @SerializedName("mod_reports") val modReports: List<String>,
        @SerializedName("author_patreon_flair") val authorPatreonFlair: Boolean,
        @SerializedName("author_flair_text_color") val authorFlairTextColor: String,
        @SerializedName("permalink") val permalink: String,
        @SerializedName("parent_whitelist_status") val parentWhitelistStatus: String,
        @SerializedName("stickied") val stickied: Boolean,
        @SerializedName("url") val url: String,
        @SerializedName("subreddit_subscribers") val subredditSubscribers: Int,
        @SerializedName("created_utc") val createdUtc: Int,
        @SerializedName("num_crossposts") val numCrossposts: Int,
        @SerializedName("media") val media: String,
        @SerializedName("is_video") val isVideo: Boolean
)