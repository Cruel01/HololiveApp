package com.example.hololiveapp.data

import com.example.hololiveapp.model.Member
import com.example.hololiveapp.model.MembersData
import com.example.hololiveapp.model.Merch
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class HoloRepo{

    private val memberHolo = mutableListOf<Merch>()

    init {
        if (memberHolo.isEmpty()) {
            MembersData.Members.forEach {
                memberHolo.add(Merch(it, 0))
            }
        }
    }

    fun getAllMembers(): Flow<List<Merch>> {
        return flowOf(memberHolo)
    }

    fun getById(merchId: Long): Merch {
        return memberHolo.first {
            it.member.id == merchId
        }
    }

    fun updateMerch(memberId: Long, newCountValue: Int): Flow<Boolean> {
        val index = memberHolo.indexOfFirst { it.member.id == memberId }
        val result = if (index >= 0) {
            val merchHolo = memberHolo[index]
            memberHolo[index] =
                merchHolo.copy(member = merchHolo.member, count = newCountValue)
            true
        } else {
            false
        }
        return flowOf(result)
    }

    fun getAllAddedMerch(): Flow<List<Merch>> {
        return getAllMembers()
            .map { merchHolo ->
                merchHolo.filter { merchHolo ->
                    merchHolo.count != 0
                }
            }
    }

    companion object {
        @Volatile
        private var instance: HoloRepo? = null

        fun getInstance(): HoloRepo =
            instance ?: synchronized(this) {
                HoloRepo().apply {
                    instance = this
                }
            }
    }
}