#!/bin/bash

IDP_HOST=idp.ssocircle.com
IDP_PORT=443
CERTIFICATE_FILE=ssocircle.cert
KEYSTORE_FILE=samlKeystore.jks
KEYSTORE_PASSWORD=ssocircle
KEYSTORE_ALIAS=nalle123

openssl req -newkey rsa:2048 -x509 -keyout cakey.pem -out cacert.pem -days 3650
openssl pkcs12 -export -in cacert.pem -inkey cakey.pem -out identity.p12 -name $KEYSTORE_ALIAS
keytool -importkeystore -destkeystore $KEYSTORE_FILE -deststorepass $KEYSTORE_PASSWORD -srckeystore identity.p12 -srcstoretype PKCS12 -srcstorepass $KEYSTORE_PASSWORD
keytool -import -file cacert.pem -keystore $KEYSTORE_FILE -storepass $KEYSTORE_PASSWORD



