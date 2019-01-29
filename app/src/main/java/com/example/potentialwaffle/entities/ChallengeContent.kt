package com.example.potentialwaffle.entities

import java.util.ArrayList
import java.util.HashMap

object ChallengeContent {
  //TODO create items from excel

  /**
   * An array of sample (dummy) items.
   */
  val ITEMS: MutableList<ChallengeItem> = ArrayList()

  /**
   * A map of sample (dummy) items, by ID.
   */
  val ITEM_MAP: MutableMap<String, ChallengeItem> = HashMap()

  private val COUNT = 12

  init {
    // Add some sample items.
    for (i in 1..COUNT) {
      addItem(createDummyItem(i))
    }
  }

  private fun addItem(item: ChallengeItem) {
    ITEMS.add(item)
    ITEM_MAP.put(item.id, item)
  }

  private fun createDummyItem(position: Int): ChallengeItem {
    return ChallengeItem(
      position.toString(),
      "test",
      "test statement " + position.toString(),
      "funn",
      mutableMapOf(ResponseOptions.A to "x", ResponseOptions.B to "y", ResponseOptions.C to "zz", ResponseOptions.D to "zz"),
      ResponseOptions.A,
      RewardType.Video,
      "funn"
    )
  }

  private fun makeDetails(position: Int): String {
    val builder = StringBuilder()
    builder.append("Details about Item: ").append(position)
    for (i in 0..position - 1) {
      builder.append("\nMore details information here.")
    }
    return builder.toString()
  }

  data class ChallengeItem(
    val id: String,
    val challenger: String,
    val statementText: String,
    val statementVideo: String,
    val options: MutableMap<ResponseOptions, String>,
    val correctOption: ResponseOptions,
    val rewardType: RewardType,
    val reward: String
  ) {
    override fun toString(): String = "Question: $statementText by: $challenger, " +
      "Correct response: $correctOption" +
      "Reward: $reward of type $rewardType"
  }

  enum class ResponseOptions {
    A,
    B,
    C,
    D
  }

  enum class RewardType {
    Video,
    Countdown,
    Present
  }
}
