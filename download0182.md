# Hosted by OpenSourceMasters #

[OpenSource Masters.com is hosting the public artifact repository.](http://opensourcemasters.com/)

# Download Link #
  * [Click here to download HBase Writer 0.18.2](http://repo.opensourcemasters.com:8080/nexus/content/repositories/releases/org/archive/hbase-writer/0.18.2/)
  * Release 0.18.2 - 03.12.2008
  * 3rd Release - Compatible with hadoop-0.18.2, hbase-0.18.1 & heritrix 2.0.1
  * ISSUE-6 - Adding maximum content size support.  If content items collected by heritrix exceed the hard-coded limit of 20MB, then the writer will ignore fetching this item.  This is to prevent HBase from hanging on large record writes.  (Patch submitted by Andrew Purtell via Ryan Smith)
  * ISSUE-5 - If HBase throws an exception, the Hbase-Writer is not added back to the writer pool.  (Patch submitted by Andrew Purtell via Ryan Smith)