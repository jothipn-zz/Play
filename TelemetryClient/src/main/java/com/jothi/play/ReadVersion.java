package com.jothi.play;

import com.microsoft.applicationinsights.TelemetryClient;

/**
 Display package name and version information for javax.mail.internet.
 */
public final class ReadVersion {
    public static void main(String... aArgs) {
        ReadVersion readVersion = new ReadVersion();
        readVersion.readVersionInfoInManifest();
    }

    public void readVersionInfoInManifest() {
        TelemetryClient tc = new TelemetryClient();
        Package objPackage = tc.getClass().getPackage();
        //examine the package object
        String name = objPackage.getSpecificationTitle();
        String version = objPackage.getSpecificationVersion();
        //some jars may use 'Implementation Version' entries in the manifest instead
        System.out.println("Package name: " + name);
        System.out.println("Package version: " + version);
    }
}