# Wavefront Slug Rison Generator
This library generates [Rison](https://rison.io/#:~:text=What%20is%20Rison,metaweb(acquired%20by%20google))
slugs for Wavefront charts/dashboards. With it, you can compose a validated Wavefront URL by:
* Creating a new chart/dashboard slug using the `SlugBuilders` factory.
* Combining the Wavefront hostname and the new slug.

Example:
![example](slug_intro.png)

## Table of Contents
* [Prerequisites](#prerequisites)
* [Generate a Chart Slug](#generate-a-chart-slug)
  * [Get Wavefront Hostname to Create the Base URL](#1-get-the-wavefront-hostname-to-create-the-base-url)
  * [Generate Chart Slug](#2-generate-the-chart-slug)
  * [Generate Chart URL](#3-generate-the-chart-url)
* [Generate a Dashboard Slug](#generate-a-dashboard-slug)
  * [Get Wavefront Hostname and Dashboard ID to Create the Base URL](#1-get-the-wavefront-hostname-and-the-dashboard-id-to-create-the-base-url)
  * [Generate Dashboard Slug](#2-generate-the-dashboard-slug)
  * [Generate Dashboard URL](#3-generate-the-dashboard-url)
* [License](#license)
* [How to Contribute](#how-to-contribute)

## Prerequisites
* Java 11 or above
* If you are using Maven, add the following dependency:
    ```xml
    <dependency>
      <groupId>com.wavefront</groupId>
      <artifactId>slug-generator</artifactId>
      <version>$releaseVersion</version>
    </dependency>
    ```
    Replace `$releaseVersion` with the latest version available on [maven-central](https://oss.sonatype.org/).
* If you are using Gradle, add the following dependency:
    ```groovy
    dependencies {
    // Pick one:

    // 1. Use slug generator in your implementation only:
        implementation("com.wavefront:slug-generator:$releaseVersion")

    // 2. Use slug generator in your public API:
        api("com.wavefront:slug-generator:$releaseVersion")
    }
    ```
    Replace `$releaseVersion` with the latest version available on [maven-central](https://oss.sonatype.org/).

## Generate a Chart Slug
Follow these steps to generate a valid Wavefront chart URL:

### 1. Get the Wavefront Hostname to Create the Base URL
The hostname is the Wavefront cluster or instance name. The examples in this guide use `mydomain
.wavefront.com`.

Use a `UriBuilder` to build an encoded base URL from the Wavefront hostname.

Example: Add the following dependencies to your Maven repo.

```xml
     <dependency>
        <groupId>javax.ws.rs</groupId>
        <artifactId>javax.ws.rs-api</artifactId>
        <version>2.1.1</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.jersey.core</groupId>
        <artifactId>jersey-common</artifactId>
        <version>2.28</version>
    </dependency>
```

```java
import javax.ws.rs.core.UriBuilder;
import com.google.common.net.UrlEscapers;

class Main {
    public  static void main(String[] args) {
        // ...
        String baseUrl = UriBuilder.fromUri("https://mydomain.wavefront.com").build().toString();
        // ...
    }
}
```

### 2. Generate the Chart Slug
A chart slug describes the structure of the chart in Wavefront. It is included in the chart URL
when you open a Wavefront chart on your browser.  
The example given below creates the following slug:  
`(c:(b:1,id:chart,n:Chart,ne:!t,s:!((n:source,q:'ts(metrics)',qb:!n,qbe:!f)),smp:off),g:(c:off,d:7200,g:auto,s:1373948820),t:customer)`

Example:
```java
import com.wavefront.slug.SlugBuilders;

class Main {
    public static void main(String[] args) {
        // Plain String
        // result: _v02(c:(b:1,id:chart,n:Chart,ne:!t,s:!((n:source,q:'ts(metrics)',qb:!n,qbe:!f)),smp:off),g:(c:off,d:7200,g:auto,s:1373948820),t:customer)
        String slug = SlugBuilders.chartSlugBuilder()
            .setCustomerId("customer")
            .setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC))
            .setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC))
            .addSource("source", "ts(metrics)")
            .build();
        // see the following steps
        // ...
    }
}
```

### 3. Generate the Chart URL
Combine the base URL with the chart slug you generated previously.

```java
import javax.ws.rs.core.UriBuilder;
import com.google.common.net.UrlEscapers;

class Main {
    public  static void main(String[] args) {
        // ...
        // followed from  step 1 & 2
        String escapedFragment = "#" + UrlEscapers.urlFragmentEscaper().escape(slug);

        // result: https://mydomain.wavefront.com/chart#_v02(c:(b:1,id:chart,n:Chart,ne:!t,s:!((n:source,q:'ts(metrics)',qb:!n,qbe:!f)),smp:off),g:(c:off,d:7200,g:auto,s:1373948820),t:customer)
        String fullUrl = baseUrl + escapedFragment;
    }
}
```

## Generate a Dashboard Slug
Follow these steps to generate a valid Wavefront dashboard URL:

### 1. Get the Wavefront Hostname and the Dashboard ID to Create the Base URL
The hostname is the Wavefront cluster or instance name. The examples in this guide use `mydomain
.wavefront.com`.

The dashboard ID is the Wavefront dashboard ID you set when you create the dashboard. The examples in this guide use `mydashboard`. 
> **Note**:You need to define and persist the dashboard in Wavefront before you create a
> slug for it.  
> The dashboard slug represents a set of dashboard configurations, such as time, parameter value,
> etc., while the actual dashboard definition is retrieved from the dashboard ID.  
> As a result, if the dashboard ID is invalid, the final URL redirects you to the **AllDashboards**
> page, and the dashboard slug you generated is not useful.

Use a `UriBuilder` to build an encoded base URL from the Wavefront hostname and the
 Wavefront dashboard ID.

Example: Add the following dependencies to your Maven repo.

```xml
     <dependency>
        <groupId>javax.ws.rs</groupId>
        <artifactId>javax.ws.rs-api</artifactId>
        <version>2.1.1</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish.jersey.core</groupId>
        <artifactId>jersey-common</artifactId>
        <version>2.28</version>
    </dependency>
```

```java
import javax.ws.rs.core.UriBuilder;
import com.google.common.net.UrlEscapers;

class Main {
    public  static void main(String[] args) {
        // ...
        String baseUrl = UriBuilder.fromUri("https://mydomain.wavefront.com/dashboards/mydashboard").build().toString();
        // ...
    }
}
```

### 2. Generate the Dashboard Slug
A dashboard slug describes the parameters of the dashboard in Wavefront. It is included in the
 dashboard URL as the URL fragment when you open a Wavefront dashboard on your browser.  
The example given below creates the following slug:  `(g:(d:7200,l:!t,,s:1373948820))`

Example:
```java
import com.wavefront.slug.SlugBuilders;

class Main {
    public static void main(String[] args) {
        // Plain String
        // result: _v02(g:(d:7200,l:!t,,s:1373948820))
        String slug = SlugBuilders.dashboardSlugBuilder()
            .setStart(new DateTime(2013, 7, 16, 4, 27, DateTimeZone.UTC))
            .setEnd(new DateTime(2013, 7, 16, 6, 27, DateTimeZone.UTC))
            .build();
        // see the following steps
        // ...
    }
}
```

### 3. Generate the Dashboard URL
Combine the base URL with the chart slug you generated previously.
 
```java
import javax.ws.rs.core.UriBuilder;
import com.google.common.net.UrlEscapers;

class Main {
    public  static void main(String[] args) {
        // ...
        // followed from step 1 & 2
        String escapedFragment = "#" + UrlEscapers.urlFragmentEscaper().escape(slug);

        // result: https://mydomain.wavefront.com/dashboards/mydashboard#_v02(g:(d:7200,l:!t,s:1373948820))
        String fullUrl = baseUrl + escapedFragment;
    }
}
```

## License
This code is under the [Apache Licence v2](LICENSE.txt).

## How to Contribute
* Reach out to us on our public [Slack channel](https://www.wavefront.com/join-public-slack).
* If you run into any issues, let us know by creating a GitHub issue.

### Contributor License Agreement (CLA)
If you wish to contribute code, and you have not signed our contributor license agreement (CLA), our
bot will update the issue when you open a Pull Request. For any questions about the CLA process,
please refer to our [VMware CLA FAQ](https://cla.vmware.com/faq).
