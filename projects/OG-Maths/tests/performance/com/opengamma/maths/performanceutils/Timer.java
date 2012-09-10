/**
 * Copyright (C) 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * 
 * Please see distribution for license.
 */

package com.opengamma.maths.performanceutils;

import com.opengamma.maths.commonapi.exceptions.MathsExceptionGeneric;

/**
 * A very simple timer, the logic isn't great TODO: write a better timer!
 */
public class Timer {
  private long _startTime;
  private long _stopTime;
  private long _elapsedTime;
  private long _totalTime;
  private boolean _isRunning;
  private boolean _hasBeenStarted;
  private boolean _hasBeenStopped;
  private boolean _elapsedCalled;

  /**
   * Starts the timer
   */
  public void startTimer() {
    if (_isRunning) {
      throw new MathsExceptionGeneric("Method startTimer() requested on timer that is already running");
    }
    _isRunning = true;
    _hasBeenStarted = true;
    _startTime = getCurrentTime();
  }

  /**
   * Stops the timer
   */
  public void stopTimer() {
    if (!_isRunning) {
      throw new MathsExceptionGeneric("Method stopTimer() requested but the timer is not running.");
    }
    _isRunning = false;
    _hasBeenStopped = true;
    _stopTime = getCurrentTime();
  }

  /**
   * Reports the time elapsed since start
   * @return time elapsed since start [ns]
   */
  public long elapsedTime() {
    if (!_hasBeenStarted) {
      throw new MathsExceptionGeneric("Method elapsedTime() requested but the timer has not been started OR has been reset.");
    }
    _elapsedTime = getCurrentTime() - _startTime;
    _elapsedCalled = true;
    return _elapsedTime;
  }

  /**
   * Reports the total elapsed since between start & stop being called
   * @return total time [ns]
   */
  public long totalTime() {
    if (!_hasBeenStarted || !_hasBeenStopped) {
      throw new MathsExceptionGeneric("Method totalTime() requested but the timer has not been started and stopped.");
    }
    _totalTime = _stopTime - _startTime;
    return _totalTime;
  }

  /**
   * Reports the split time, 
   * if elapsedTime() has not been called before it reports the time since start
   * else it reports the time since the last call to elapsedTime()
   * this data is not stored and is valid only at time of call.  
   * @return the split time.
   */
  public long splitTime() {
    if (!_hasBeenStarted) {
      throw new MathsExceptionGeneric("Method splitTime() requested but the timer has not been started OR has been reset.");
    }
    if (!_elapsedCalled) {
      return getCurrentTime() - _startTime;
    } else {
      return getCurrentTime() - _elapsedTime;
    }
  }

  private long getCurrentTime() {
    return System.nanoTime();
  }
}
