# Hosted by OpenSourceMasters #

[OpenSource Masters.com is hosting the public artifact repository.](http://opensourcemasters.com/)

# Download Link #
  * [Click here to download HBase Writer 0.98.7-RELEASE](http://repo.opensourcemasters.com:8080/nexus/content/repositories/releases/org/archive/hbase-writer/0.98.7-RELEASE/)
  * Release 0.98.7 - 23.11.2014
  * Added support to extend this library easier.  Before you had to extend HbaseWriter class or fork and maintain a separate branch to add custom logic.  Now you can just extend HbaseWriterProcessor and reference your custom extended processor class from the heritrix job config.
  * Added wrapper method so all data added to the put object gets serialized if a serializer is specified in the configurable parameters. Also ensuring both request and response streams are closed at the end of the write to the table. Both have the potential to be open if the custom method is used and streams are not closed, now this shouldn't be a problem.
  * Added bug fix to shouldProcess() method. If a record got an IOException, it was logging the record as an error but still trying to process the record. Now it returns false and the record wont be processed. Also updated comments.
  * Logging heritrix annotations if any are encountered during the crawl.  For example if a fetched url has data larger than the max size limit, no data will be written but an annotation "c:an" => "size" will get written now instead.
  * Shortened the default table column names to be more reasonable. Added a configurable delimiter when annotations are present.
  * Bug fixes, checking for null and allowing setter for column name variables.  Moving properties to top of pom.xml file.  Updating maven plugin versions to their latest versions.  Added support to use latest 	version of hadoop and hbase and heritrix dependencies.
  * Now including a list of dependencies to make it easier to update hbase-writer dependencies inside of heritrix/lib