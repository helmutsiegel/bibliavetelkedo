#!/usr/bin/env bash
mysql -u"root" -p"root" < dbcreate.sql
mysql -u"root" -p"root" bibliavetelkedo < create_tables.sql
mysql -u"root" -p"root" bibliavetelkedo < insert_data.sql
