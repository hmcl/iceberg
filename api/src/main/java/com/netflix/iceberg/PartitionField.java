/*
 * Copyright 2017 Netflix, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.netflix.iceberg;

import com.google.common.base.Objects;
import com.netflix.iceberg.transforms.Transform;
import java.io.Serializable;

/**
 * Represents a single field in a {@link PartitionSpec}.
 */
public class PartitionField implements Serializable {
  private final int sourceId;
  private final String name;
  private final Transform<?, ?> transform;

  PartitionField(int sourceId, String name, Transform<?, ?> transform) {
    this.sourceId = sourceId;
    this.name = name;
    this.transform = transform;
  }

  /**
   * @return the field id of the source field in the {@link PartitionSpec spec's} table schema
   */
  public int sourceId() {
    return sourceId;
  }

  /**
   * @return the name of this partition field
   */
  public String name() {
    return name;
  }

  /**
   * @return the transform used to produce partition values from source values
   */
  public Transform<?, ?> transform() {
    return transform;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other == null || getClass() != other.getClass()) {
      return false;
    }

    PartitionField that = (PartitionField) other;
    return (
        sourceId == that.sourceId &&
        name.equals(that.name) &&
        transform.equals(that.transform)
    );
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(sourceId, name, transform);
  }
}
