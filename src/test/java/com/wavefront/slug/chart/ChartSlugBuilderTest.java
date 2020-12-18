package com.wavefront.slug.chart;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit test for {@link ChartSlugBuilderImpl}.
 * <p>
 * Mainly test for RISON Serialization of {@link ChartSlug}
 *
 * @author Yutian Wu (wyutian@vmware.com)
 */
public class ChartSlugBuilderTest {

  private ChartSlugBuilder builder;

  @BeforeEach
  public void setup() {
    this.builder = ChartSlugBuilders.chartSlugBuilder();
  }

  @Test
  @DisplayName("With source query contains angle brackets")
  public void testBuildWithAngleBrackets() throws Exception {
    String slug = builder.
        setCustomerId("tsdb").
        setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC)).
        setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC)).
        addSource("Hello", "Wo<rld").
        build();

    // verify
    String expectedSlug = "(c:(b:1,id:chart,n:Chart,ne:!t,s:!((n:Hello,q:'Wo<rld',qb:!n,qbe:!f)),smp:off),g:(c:off,d:7200,g:auto,s:1373948820),t:tsdb)";
    String message = String.format("The expected URL should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug)
        .as(message)
        .isEqualTo(expectedSlug);
  }

  @Test
  @DisplayName("With source query name contains single quote")
  public void testBuildWithSingleQuote() throws Exception {
    String slug = builder.
        setCustomerId("tsdb").
        setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC)).
        setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC)).
        addSource("'s", "World").
        build();

    // verify
    String expectedSlug = "(c:(b:1,id:chart,n:Chart,ne:!t,s:!((n:'!'s',q:World,qb:!n,qbe:!f)),smp:off),g:(c:off,d:7200,g:auto,s:1373948820),t:tsdb)";
    String message = String.format("The expected URL should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug)
        .as(message)
        .isEqualTo(expectedSlug);
  }

  @Test
  @DisplayName("With source query name contains double quote")
  public void testBuildWithDoubleQuote() throws Exception {
    String slug = builder.
        setCustomerId("tsdb").
        setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC)).
        setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC)).
        addSource("\"s", "World").
        build();

    // verify
    String expectedSlug = "(c:(b:1,id:chart,n:Chart,ne:!t,s:!((n:'\"s',q:World,qb:!n,qbe:!f)),smp:off),g:(c:off,d:7200,g:auto,s:1373948820),t:tsdb)";
    String message = String.format("The expected URL should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug)
        .as(message)
        .isEqualTo(expectedSlug);
  }

  @Test
  @DisplayName("With source query contains white spaces")
  public void testBuildWithWhiteSpace() throws Exception {

    String slug = builder.
        setCustomerId("tsdb").
        setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC)).
        setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC)).
        setGranularity("h").
        setId("id").
        setName("Super").
        setBase(10).
        setCompare("1d").
        addSource("Hello", "World Wide Web").
        addSource("Hello2", "World2", false, "QBS", true).
        addFocusedHost("abcd01").
        build();

    // verify
    String expectedSlug = "(c:(b:10,id:id,n:Super,ne:!t,s:!((n:Hello,q:'World Wide Web',qb:!n,qbe:!f)," +
        "(n:Hello2,q:World2,qb:QBS,qbe:!t)),smp:off),g:(c:'1d',d:7200,g:h,s:1373948820),h:!(abcd01)," +
        "t:tsdb)";
    String message = String.format("The expected URL should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug)
        .as(message)
        .isEqualTo(expectedSlug);
  }

  /**
   * the returned encoded url should contain `d:!t` in it, this field is omitted in the url if it's
   * not disabled (see {@link ChartSource}).
   */
  @Test
  @DisplayName("With disabled source")
  public void testBuildWithDisabledSource() throws Exception {
    String slug = builder.
        setCustomerId("tsdb").
        setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC)).
        setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC)).
        setGranularity("h").
        setId("id").
        setName("Super").
        setBase(10).
        setCompare("1d").
        addSource("Hello", "World Wide Web", true). // disabled
        addSource("Hello2", "World2", false, "QBS", true).
        addFocusedHost("abcd01").
        build();

    // verify
    // notes "d:!t" wedged in below
    String expectedSlug = "(c:(b:10,id:id,n:Super,ne:!t,s:!((d:!t,n:Hello,q:'World Wide Web',qb:!n,qbe:!f)," +
        "(n:Hello2,q:World2,qb:QBS,qbe:!t)),smp:off),g:(c:'1d',d:7200,g:h,s:1373948820),h:!(abcd01)," +
        "t:tsdb)";
    String message = String.format("The expected URL should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug)
        .as(message)
        .isEqualTo(expectedSlug);
  }

  /**
   * In ticket [MONIT-3693] Alert does not fire, but cloned version does, nested double quotes does
   * not escaped correctly which leads to JavaScript syntax issue.
   */
  @Test
  @DisplayName("With nested double quote")
  public void testBuildWithNestedDoubleQuote() throws Exception {
    String slug = builder.
        setCustomerId("igneous-systems").
        setStart(new DateTime(2016, 5, 23, 4, 27, DateTimeZone.UTC)).
        setEnd(new DateTime(2016, 5, 23, 6, 27, DateTimeZone.UTC)).
        setGranularity("h").
        setId("new-chart").
        setName("New Chart").
        setBase(10).
        addSource("Hello", "mcount(2m, ts(igneous.heartbeat.count.30s, \"_host\"=\"petra1\"))", false,
            "{\"_v\":1,\"metric\":\"ts(igneous.heartbeat.count.30s)\",\"filters\":[[[\"\\\"_host\\\"\",\"petra1\"]]," +
                "\"and\"],\"functions\":[[\"mcount\",[\"2m\"]]]}",
            true).
        build();

    // verify
    String expectedSlug = "(c:(b:10,id:new-chart,n:'New Chart',ne:!t,s:!(" +
        "(n:Hello,q:'mcount(2m, ts(igneous.heartbeat.count.30s, \"_host\"=\"petra1\"))',qb:'{\"_v\":1,\"metric\":\"ts(igneous.heartbeat." +
        "count.30s)\",\"filters\":[[[\"\\\"_host\\\"\",\"petra1\"]],\"and\"],\"functions\":[[\"mcount\",[\"2m\"]]]}'" +
        ",qbe:!t)),smp:off),g:(c:off,d:7200,g:h,s:1463977620),t:igneous-systems)";
    String message = String.format("The expected URL should be %s while it is %s.", expectedSlug, slug);
    assertThat(slug)
        .as(message)
        .isEqualTo(expectedSlug);
  }
}
