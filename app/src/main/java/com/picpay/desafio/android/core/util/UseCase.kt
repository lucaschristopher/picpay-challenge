package com.picpay.desafio.android.core.util

import kotlinx.coroutines.flow.Flow

/**
 * This abstract class represents a generic use case definition.
 * @param Param can represent a query for searches, for example
 * @param Source represents the expected return type (will be converted to Flow)
 */
abstract class UseCase<Param, Source> {

    /**
     * This is the standard method, taking a parameter and returning an object of type Flow<Source>
     */
    abstract suspend fun execute(param: Param): Flow<Source>

    /**
     * An optimization using the operator modifier to simplify syntax at the point of use
     */
    suspend operator fun invoke(param: Param) = execute(param)

    /**
     * This subclass allows you to create UseCases for queries that do not have parameters
     * (like the most basic application query).
     * For it to work we pass the object "None" in place of Param.
     */
    abstract class NoParam<Source> : UseCase<None, Flow<Source>>() {

        /**
         * Default method for a query with no parameters.
         * NOTE: This method does not override the execute() defined in the parent class!
         */
        abstract suspend fun execute(): Flow<Source>

        /**
         * As this class inherits from UseCase, I am required to implement the execute() method with parameter.
         * In this case, I use the final modifier to lock the method.
         * The function returns an exception.
         */
        final override suspend fun execute(param: None) =
            throw UnsupportedOperationException()

        /**
         * An optimization of the call using "operator"
         */
        suspend operator fun invoke(): Flow<Source> = execute()
    }

    /**
     * An empty object just to make it possible to create the NoParam class
     * as a child of UseCase with the same syntax.
     */
    object None
}