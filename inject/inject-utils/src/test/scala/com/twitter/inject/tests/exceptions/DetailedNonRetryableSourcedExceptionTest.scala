package com.twitter.inject.tests.exceptions

import com.twitter.inject.WordSpecTest
import com.twitter.inject.exceptions.DetailedNonRetryableSourcedException

class DetailedNonRetryableSourcedExceptionTest extends WordSpecTest {

  "DetailedNonRetryableSourcedException" should {

    "log details with no source" in {
      val e = new DetailedNonRetryableSourcedException("Non-retryable exception occurred.")
      e.toDetailsString should be(Seq(e.getClass.getSimpleName).mkString("/"))
    }

    "log details with source" in {
      val detailedNonRetryableSource = "SomeProject"
      val e = new DetailedNonRetryableSourcedException("Non-retryable exception occurred.") {
        override val source = detailedNonRetryableSource
      }

      e.toDetailsString should be(s"$detailedNonRetryableSource/" + Seq(e.getClass.getSimpleName).mkString("/"))
    }

    "toString" in {
      val e = new DetailedNonRetryableSourcedException("Non-retryable exception occurred.")
      e.toString should be("com.twitter.inject.exceptions.DetailedNonRetryableSourcedException: Non-retryable exception occurred.")
    }

    "toString with Product" in {
      val e = new TestException("Non-retryable exception occurred.")
      e.toString should be ("TestException(Non-retryable exception occurred.)")
    }
  }
}

case class TestException(override val message: String) extends DetailedNonRetryableSourcedException(message)
