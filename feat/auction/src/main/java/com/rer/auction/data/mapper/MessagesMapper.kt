package com.rer.auction.data.mapper

import com.rer.auction.data.model.response.ChatResponseItemModel
import com.rer.auction.domain.entity.ChatEntity

object MessagesMapper {
    object Entity {
        fun List<ChatResponseItemModel>?.toEntity(): List<ChatEntity> {
            return this?.map { it.toEntity() } ?: emptyList()
        }

        private fun ChatResponseItemModel.toEntity(): ChatEntity {
            return ChatEntity(
                user = this.user.orEmpty(),
                message = this.message.orEmpty(),
                timestamp = this.timestamp.orEmpty()
            )
        }
    }

}