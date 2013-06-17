yara
====

Yet Another REST API für Atlassian Fisheye
The API allows to create and delete a repository over HTTP.

Usage:
http://<YOUR-FISHEYE-REST-CONTEXT>/yara/1.0/repository/create/<NAME>/<URL>
http:// http://x10000760:3990/fecru/rest/yara/1.0/repository/create/bla/file:!!!C:!dev!git!bla
(forward slashes are replaced with !)

http://<YOUR-FISHEYE-REST-CONTEXT>/yara/1.0/repository/delete/<NAME>
http:// http://x10000760:3990/fecru/rest/yara/1.0/repository/create/bla


Prerequisites
You need to install the Atlassian Plugin SDK
https://developer.atlassian.com/display/DOCS/Introduction+to+the+Atlassian+Plugin+SDK
If you use Eclipse, we provide launch scripts, which rely on the ATLAS_HOME environment variable.

