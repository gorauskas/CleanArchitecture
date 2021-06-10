package com.erikschouten.cleanarchitecture.repositories.user

import com.erikschouten.cleanarchitecture.domain.AlreadyExistsException
import com.erikschouten.cleanarchitecture.domain.entity.user.Email
import com.erikschouten.cleanarchitecture.domain.entity.user.User
import com.erikschouten.cleanarchitecture.domain.repository.UserRepository
import com.erikschouten.cleanarchitecture.repositories.DatabaseFactory.query
import java.util.*

class UserRepositoryImpl : UserRepository {
    override suspend fun findByEmail(email: Email) = query {
        UserEntity.find { UserTable.email eq email.value }.firstOrNull()?.toUser()
    }

    override suspend fun findById(id: UUID) = query {
        UserEntity.findById(id)?.toUser()
    }

    override suspend fun findAll() = query {
        UserEntity.all().map { it.toUser() }
    }

    override suspend fun create(entity: User) = query {
        UserEntity.new {
            email = entity.email
            authorities = entity.authorities
            password = entity.password
        }.toUser()
    }

    override suspend fun update(entity: User) = query {
        UserEntity[entity.id].apply {
            email = entity.email
            authorities = entity.authorities
            password = entity.password
        }.toUser()
    }

    override suspend fun delete(id: UUID) = query {
        UserEntity[id].delete()
    }

    override suspend fun count() = query {
        UserEntity.count()
    }
}

class InMemoryUserRepository : UserRepository {
    private val users = mutableMapOf<UUID, User>()

    override suspend fun findById(id: UUID) = users[id]
    override suspend fun findAll() = users.values.toList()
    override suspend fun create(entity: User): User {
        if (users.containsKey(entity.id)) throw AlreadyExistsException()
        return update(entity)
    }

    override suspend fun update(entity: User): User {
        users[entity.id] = entity
        return entity
    }

    override suspend fun delete(id: UUID) {
        users.remove(id)
    }

    override suspend fun count() = users.size.toLong()

    override suspend fun findByEmail(email: Email) = users.values.find { it.email == email }
}