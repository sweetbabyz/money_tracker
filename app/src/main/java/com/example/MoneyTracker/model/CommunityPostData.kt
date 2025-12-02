package com.example.jizhangbao.model
import com.google.gson.annotations.SerializedName


data class CommunityPostData(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("error")
    val error: Int,
    @SerializedName("total")
    val total: Int
) {
    data class Data(
        @SerializedName("advertisement_url")
        val advertisementUrl: String,
        @SerializedName("article_limit_free")
        val articleLimitFree: Boolean,
        @SerializedName("article_limit_free_etime")
        val articleLimitFreeEtime: Int,
        @SerializedName("article_limit_free_stime")
        val articleLimitFreeStime: Int,
        @SerializedName("author")
        val author: Author,
        @SerializedName("banner")
        val banner: String,
        @SerializedName("belong_to_member")
        val belongToMember: Boolean,
        @SerializedName("comment_count")
        val commentCount: Int,
        @SerializedName("corner")
        val corner: Corner,
        @SerializedName("created_time")
        val createdTime: Int,
        @SerializedName("free")
        val free: Boolean,
        @SerializedName("id")
        val id: Int,
        @SerializedName("id_hash")
        val idHash: String,
        @SerializedName("important")
        val important: Int,
        @SerializedName("is_matrix")
        val isMatrix: Boolean,
        @SerializedName("is_pre_recommend_to_home")
        val isPreRecommendToHome: Boolean,
        @SerializedName("is_recommend_to_home")
        val isRecommendToHome: Boolean,
        @SerializedName("issue")
        val issue: String,
        @SerializedName("like_count")
        val likeCount: Int,
        @SerializedName("modify_time")
        val modifyTime: Int,
        @SerializedName("morning_paper_title")
        val morningPaperTitle: List<Any>,
        @SerializedName("object_type")
        val objectType: Int,
        @SerializedName("podcast_duration")
        val podcastDuration: Int,
        @SerializedName("post_type")
        val postType: Int,
        @SerializedName("recommend_to_home_at")
        val recommendToHomeAt: Int,
        @SerializedName("released_time")
        val releasedTime: Int,
        @SerializedName("series")
        val series: List<Any>,
        @SerializedName("slug")
        val slug: String,
        @SerializedName("special_columns")
        val specialColumns: List<Any>,
        @SerializedName("status")
        val status: Int,
        @SerializedName("summary")
        val summary: String,
        @SerializedName("tags")
        val tags: List<Tag>,
        @SerializedName("title")
        val title: String,
        @SerializedName("user_member_card_show_on")
        val userMemberCardShowOn: Boolean,
        @SerializedName("view_count")
        val viewCount: Int
    ) {
        data class Author(
            @SerializedName("avatar")
            val avatar: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("nickname")
            val nickname: String,
            @SerializedName("slug")
            val slug: String
        )

        data class Corner(
            @SerializedName("color")
            val color: String,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("memo")
            val memo: String,
            @SerializedName("name")
            val name: String,
            @SerializedName("url")
            val url: String
        )

        data class Tag(
            @SerializedName("articles")
            val articles: Any,
            @SerializedName("articles_count")
            val articlesCount: Int,
            @SerializedName("color")
            val color: String,
            @SerializedName("created_at")
            val createdAt: Int,
            @SerializedName("custom_url")
            val customUrl: String,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("icon_id")
            val iconId: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("intro")
            val intro: String,
            @SerializedName("modify_at")
            val modifyAt: Int,
            @SerializedName("pai_read_recommend_on")
            val paiReadRecommendOn: Boolean,
            @SerializedName("recommend")
            val recommend: Boolean,
            @SerializedName("released_at")
            val releasedAt: Int,
            @SerializedName("synonyms")
            val synonyms: String,
            @SerializedName("tags")
            val tags: Any,
            @SerializedName("title")
            val title: String,
            @SerializedName("usable_member")
            val usableMember: Boolean,
            @SerializedName("usable_user")
            val usableUser: Boolean,
            @SerializedName("views_count")
            val viewsCount: Int,
            @SerializedName("weight")
            val weight: Int
        )
    }
}