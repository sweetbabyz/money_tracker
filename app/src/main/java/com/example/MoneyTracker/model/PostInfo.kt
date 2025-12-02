package com.example.jizhangbao.model
import com.google.gson.annotations.SerializedName


data class PostInfo(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("error")
    val error: Int
) {
    data class Data(
        @SerializedName("allow_comment")
        val allowComment: Boolean,
        @SerializedName("article_advertisement")
        val articleAdvertisement: Any,
        @SerializedName("article_column")
        val articleColumn: Any,
        @SerializedName("article_count")
        val articleCount: ArticleCount,
        @SerializedName("article_follow_up_admin")
        val articleFollowUpAdmin: Any,
        @SerializedName("article_limit_free")
        val articleLimitFree: Boolean,
        @SerializedName("article_limit_free_etime")
        val articleLimitFreeEtime: Int,
        @SerializedName("article_limit_free_stime")
        val articleLimitFreeStime: Int,
        @SerializedName("article_type")
        val articleType: Int,
        @SerializedName("author")
        val author: Author,
        @SerializedName("author_seconds")
        val authorSeconds: List<Any>,
        @SerializedName("author_series_article_manage_on")
        val authorSeriesArticleManageOn: Boolean,
        @SerializedName("banner")
        val banner: String,
        @SerializedName("benefits_statement")
        val benefitsStatement: BenefitsStatement,
        @SerializedName("body")
        val body: String,
        @SerializedName("column_author")
        val columnAuthor: Boolean,
        @SerializedName("comment_file_on")
        val commentFileOn: Boolean,
        @SerializedName("comment_manage_permission")
        val commentManagePermission: Boolean,
        @SerializedName("comments")
        val comments: Any,
        @SerializedName("copyright")
        val copyright: Boolean,
        @SerializedName("corner")
        val corner: Any,
        @SerializedName("favorited")
        val favorited: Boolean,
        @SerializedName("id")
        val id: Int,
        @SerializedName("important")
        val important: Int,
        @SerializedName("is_contributed_by_signed_writer")
        val isContributedBySignedWriter: Boolean,
        @SerializedName("is_expire")
        val isExpire: Boolean,
        @SerializedName("is_free")
        val isFree: Boolean,
        @SerializedName("is_pay")
        val isPay: Boolean,
        @SerializedName("is_preview")
        val isPreview: Boolean,
        @SerializedName("issue")
        val issue: String,
        @SerializedName("keywords")
        val keywords: String,
        @SerializedName("liked")
        val liked: Boolean,
        @SerializedName("member")
        val member: Any,
        @SerializedName("member_series")
        val memberSeries: Any,
        @SerializedName("member_series_cog")
        val memberSeriesCog: Any,
        @SerializedName("member_series_pre")
        val memberSeriesPre: Any,
        @SerializedName("morning_paper_title")
        val morningPaperTitle: Any,
        @SerializedName("next_article_banner")
        val nextArticleBanner: String,
        @SerializedName("next_article_slug")
        val nextArticleSlug: String,
        @SerializedName("next_article_title")
        val nextArticleTitle: String,
        @SerializedName("next_artitle_id")
        val nextArtitleId: Int,
        @SerializedName("podcast_configs")
        val podcastConfigs: Any,
        @SerializedName("podcast_duration")
        val podcastDuration: Int,
        @SerializedName("podcast_guests")
        val podcastGuests: Any,
        @SerializedName("podcast_url")
        val podcastUrl: String,
        @SerializedName("post_type")
        val postType: Int,
        @SerializedName("pre_article_banner")
        val preArticleBanner: String,
        @SerializedName("pre_article_slug")
        val preArticleSlug: String,
        @SerializedName("pre_article_title")
        val preArticleTitle: String,
        @SerializedName("pre_artitle_id")
        val preArtitleId: Int,
        @SerializedName("probation")
        val probation: Boolean,
        @SerializedName("promote_image")
        val promoteImage: String,
        @SerializedName("promote_title")
        val promoteTitle: String,
        @SerializedName("pub_date")
        val pubDate: String,
        @SerializedName("released_time")
        val releasedTime: Int,
        @SerializedName("rtime")
        val rtime: String,
        @SerializedName("series")
        val series: Any,
        @SerializedName("series_use_end_time")
        val seriesUseEndTime: Int,
        @SerializedName("series_use_start_time")
        val seriesUseStartTime: Int,
        @SerializedName("show_content_table")
        val showContentTable: Boolean,
        @SerializedName("sign_contract_request_fields")
        val signContractRequestFields: String,
        @SerializedName("special_columns")
        val specialColumns: List<Any>,
        @SerializedName("summary")
        val summary: String,
        @SerializedName("summary_weibo")
        val summaryWeibo: String,
        @SerializedName("tags")
        val tags: List<Tag>,
        @SerializedName("thread")
        val thread: Boolean,
        @SerializedName("title")
        val title: String,
        @SerializedName("title_prefix")
        val titlePrefix: String,
        @SerializedName("update_details")
        val updateDetails: List<Any>,
        @SerializedName("user_is_pay")
        val userIsPay: Boolean,
        @SerializedName("video_code")
        val videoCode: String,
        @SerializedName("video_info")
        val videoInfo: String,
        @SerializedName("video_platform_type")
        val videoPlatformType: Int,
        @SerializedName("words")
        val words: Int
    ) {
        data class ArticleCount(
            @SerializedName("comment_count")
            val commentCount: Int,
            @SerializedName("like_count")
            val likeCount: Int,
            @SerializedName("show_views_count")
            val showViewsCount: Boolean,
            @SerializedName("views_count")
            val viewsCount: Int
        )

        data class Author(
            @SerializedName("avatar")
            val avatar: String,
            @SerializedName("bio")
            val bio: String,
            @SerializedName("followed")
            val followed: Boolean,
            @SerializedName("id")
            val id: Int,
            @SerializedName("nickname")
            val nickname: String,
            @SerializedName("slug")
            val slug: String,
            @SerializedName("user_badges")
            val userBadges: List<Any>,
            @SerializedName("user_flags")
            val userFlags: List<UserFlag>,
            @SerializedName("user_plan_flags")
            val userPlanFlags: List<UserPlanFlag>
        ) {
            data class UserFlag(
                @SerializedName("admin_id")
                val adminId: Int,
                @SerializedName("allow_add_user")
                val allowAddUser: Boolean,
                @SerializedName("allow_delete_user")
                val allowDeleteUser: Boolean,
                @SerializedName("allow_update_status")
                val allowUpdateStatus: Boolean,
                @SerializedName("color")
                val color: String,
                @SerializedName("created_at")
                val createdAt: Int,
                @SerializedName("description")
                val description: String,
                @SerializedName("icon")
                val icon: String,
                @SerializedName("icon_3d")
                val icon3d: String,
                @SerializedName("icon_3d_id")
                val icon3dId: Int,
                @SerializedName("icon_app")
                val iconApp: String,
                @SerializedName("icon_app_3d")
                val iconApp3d: String,
                @SerializedName("icon_app_3d_id")
                val iconApp3dId: Int,
                @SerializedName("icon_app_id")
                val iconAppId: Int,
                @SerializedName("icon_id")
                val iconId: Int,
                @SerializedName("icon_unlighted")
                val iconUnlighted: String,
                @SerializedName("icon_unlighted_id")
                val iconUnlightedId: Int,
                @SerializedName("icon_unlighted_show_on")
                val iconUnlightedShowOn: Boolean,
                @SerializedName("id")
                val id: Int,
                @SerializedName("key")
                val key: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("object_id")
                val objectId: Int,
                @SerializedName("remuneration_fee")
                val remunerationFee: Int,
                @SerializedName("status")
                val status: Int,
                @SerializedName("sub_type")
                val subType: Int,
                @SerializedName("tag")
                val tag: String,
                @SerializedName("type")
                val type: Int,
                @SerializedName("updated_at")
                val updatedAt: Int,
                @SerializedName("url")
                val url: String,
                @SerializedName("user_get_on")
                val userGetOn: Boolean,
                @SerializedName("weight")
                val weight: Int
            )

            data class UserPlanFlag(
                @SerializedName("admin_id")
                val adminId: Int,
                @SerializedName("allow_add_user")
                val allowAddUser: Boolean,
                @SerializedName("allow_delete_user")
                val allowDeleteUser: Boolean,
                @SerializedName("allow_update_status")
                val allowUpdateStatus: Boolean,
                @SerializedName("color")
                val color: String,
                @SerializedName("created_at")
                val createdAt: Int,
                @SerializedName("description")
                val description: String,
                @SerializedName("icon")
                val icon: String,
                @SerializedName("icon_3d")
                val icon3d: String,
                @SerializedName("icon_3d_id")
                val icon3dId: Int,
                @SerializedName("icon_app")
                val iconApp: String,
                @SerializedName("icon_app_3d")
                val iconApp3d: String,
                @SerializedName("icon_app_3d_id")
                val iconApp3dId: Int,
                @SerializedName("icon_app_id")
                val iconAppId: Int,
                @SerializedName("icon_id")
                val iconId: Int,
                @SerializedName("icon_unlighted")
                val iconUnlighted: String,
                @SerializedName("icon_unlighted_id")
                val iconUnlightedId: Int,
                @SerializedName("icon_unlighted_show_on")
                val iconUnlightedShowOn: Boolean,
                @SerializedName("id")
                val id: Int,
                @SerializedName("key")
                val key: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("object_id")
                val objectId: Int,
                @SerializedName("remuneration_fee")
                val remunerationFee: Int,
                @SerializedName("status")
                val status: Int,
                @SerializedName("sub_type")
                val subType: Int,
                @SerializedName("tag")
                val tag: String,
                @SerializedName("type")
                val type: Int,
                @SerializedName("updated_at")
                val updatedAt: Int,
                @SerializedName("url")
                val url: String,
                @SerializedName("user_get_on")
                val userGetOn: Boolean,
                @SerializedName("weight")
                val weight: Int
            )
        }

        data class BenefitsStatement(
            @SerializedName("admin_id")
            val adminId: Int,
            @SerializedName("created_at")
            val createdAt: Int,
            @SerializedName("id")
            val id: Int,
            @SerializedName("status")
            val status: Int,
            @SerializedName("title")
            val title: String,
            @SerializedName("updated_at")
            val updatedAt: Int,
            @SerializedName("usable")
            val usable: Boolean
        )

        data class Tag(
            @SerializedName("color")
            val color: String,
            @SerializedName("icon")
            val icon: String,
            @SerializedName("id")
            val id: Int,
            @SerializedName("title")
            val title: String
        )
    }
}