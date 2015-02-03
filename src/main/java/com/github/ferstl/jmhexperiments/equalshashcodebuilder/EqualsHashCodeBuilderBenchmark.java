package com.github.ferstl.jmhexperiments.equalshashcodebuilder;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;

@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class EqualsHashCodeBuilderBenchmark {


  private static final class TestObject {
    private final long l;
    private final double d;
    private final String s;
    private final Object[] array;

    public TestObject(long l, double d, String s, Object[] array) {
      this.l = l;
      this.d = d;
      this.s = s;
      this.array = array;
    };

    public boolean equalsPlain(Object o) {
      if (o == this) { return true; }
      if (!(o instanceof TestObject)) { return false; }

      TestObject other = (TestObject) o;
      return this.l == other.l
          && this.d == other.d
          && Objects.equals(this.s, other.s)
          && Arrays.equals(this.array, other.array);
    }

    // generated by Eclipse
    public int hashCodePlain() {
      final int prime = 31;
      int result = 1;
      result = prime * result + Arrays.hashCode(this.array);
      long temp;
      temp = Double.doubleToLongBits(this.d);
      result = prime * result + (int) (temp ^ (temp >>> 32));
      result = prime * result + (int) (this.l ^ (this.l >>> 32));
      result = prime * result + ((this.s == null) ? 0 : this.s.hashCode());
      return result;
    }

    public boolean equalsWithBuilder(Object o) {
      if (o == this) { return true; }
      if (!(o instanceof TestObject)) { return false; }

      TestObject other = (TestObject) o;

      return new EqualsBuilder()
        .append(this.l, other.l)
        .append(this.d, other.d)
        .append(this.s, other.s)
        .append(this.array, other.array)
        .build();
    }

    public int hashCodeWithBuilder() {
      return new HashCodeBuilder()
        .append(this.l)
        .append(this.d)
        .append(this.s)
        .append(this.array)
        .build();
    }
  }
}