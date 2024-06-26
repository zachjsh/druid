/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.druid.segment.incremental;

import org.apache.druid.java.util.common.parsers.ParseException;

import javax.annotation.Nullable;

public class IncrementalIndexAddResult
{
  private final int rowCount;
  private final long bytesInMemory;

  @Nullable
  private final ParseException parseException;
  @Nullable
  private final String reasonOfNotAdded;

  private IncrementalIndexAddResult(
      int rowCount,
      long bytesInMemory,
      @Nullable ParseException parseException,
      @Nullable String reasonOfNotAdded
  )
  {
    this.rowCount = rowCount;
    this.bytesInMemory = bytesInMemory;
    this.parseException = parseException;
    this.reasonOfNotAdded = reasonOfNotAdded;
  }

  public IncrementalIndexAddResult(
      int rowCount,
      long bytesInMemory,
      @Nullable ParseException parseException
  )
  {
    this(rowCount, bytesInMemory, parseException, null);
  }

  public IncrementalIndexAddResult(
      int rowCount,
      long bytesInMemory,
      String reasonOfNotAdded
  )
  {
    this(rowCount, bytesInMemory, null, reasonOfNotAdded);
  }

  public IncrementalIndexAddResult(
      int rowCount,
      long bytesInMemory
  )
  {
    this(rowCount, bytesInMemory, null, null);
  }

  public int getRowCount()
  {
    return rowCount;
  }

  public long getBytesInMemory()
  {
    return bytesInMemory;
  }

  public boolean hasParseException()
  {
    return parseException != null;
  }

  @Nullable
  public ParseException getParseException()
  {
    return parseException;
  }

  public boolean isRowAdded()
  {
    return reasonOfNotAdded == null && parseException == null;
  }
}
