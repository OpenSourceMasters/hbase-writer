## What is HBase-Writer? ##
HBase-Writer is a java extension to the Heritrix open source crawler.  Heritrix is written by the Internet Archive and HBase Writer enables Heritrix to store crawled content directly into HBase tables running on the Hadoop Distributed FileSystem.  By default, HBase-Writer writes crawled url content into an HBase table as individual records or "rowkeys".  Each fetched url is represented by a "rowkey" in an HBaase table.  However, HBase-Writer can easily be extended for custom behavior, like writing to multiple tables or anything else.  In turn, these HBase tables are directly supported by the MapReduce framework via Hadoop.  HBase-Writer's goal is to facilitate in fast large distributed crawls using Heritrix and to save and manage Web-scale content using HBase.


## News ##
### November 23, 2014 ###
HBase-Writer 0.98.7 has now been released.  Many significant improvements were made in this update.  Tested against Heritrix-3.2.0, Hadoop-2.4.0, HBase-0.98.7-hadoop2 and ZooKeeper-3.4.6. Added support to make the library usage easier.  Now only extending one class, HBaseWriterProcessor, is required if you want custom usage.  An example can be found [here](http://opensourcemasters.com/articles-mainmenu-57/48-hbase-writer-0-98-7-is-released.html).  Added support to log Heritrix annotations encountered during crawling to the output table.  Added a list of dependencies in a text file.   These dependencies are to be added to Heritrix/lib.  This is to make upgrading and maintenance easier.  Added a few bug fixes and made the default column names something more sensible.  More details can be found in the [CHANGELOG.txt](https://code.google.com/p/hbase-writer/source/browse/tags/HBASE_WRITER_0_98_7/CHANGELOG.txt)

### September 1st, 2013 ###
HBase-Writer 0.94.0 has now been released.  This release fixes an exception handling bug in the makeWriter() method.  Also the latest version of HBase (0.95.1) and Heritrix (3.1.1) have been built and tested against hbase-writer.  Several new dependencies have been introduced and as a result, so the README documents have been updated to reflect the new changes.

### January 22nd, 2012 ###
HBase-Writer 0.90.4 has now been released.  This version was a major bug fix to the previous version.  The last release inadvertently removed resource and connection pooling.  [Greg Lu](http://www.greglu.com/) was good enough to catch this and nice enough to share the patch with hbase-writer.  A big thank you to Greg Lu for doing great work maintaining the integrity of this plugin.  Hbase clients are being reused and connections are being closed under the new implementation.  Next on the list is to create some unit tests to ensure pooling works as expected.

### November 16th, 2011 ###
HBase-Writer 0.90.3 has now been released. This version is a compatibility upgrade to support the latest versions of HBase and Heritrix which today are HBase-0.90.3 and Heritrix-3.1.0.  Great thanks to Karthik MV with Infiniti-Research for submitting a compatibility patch!

[News Archive](NewsArchive.md)