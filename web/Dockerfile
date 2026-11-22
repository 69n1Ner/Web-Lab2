FROM httpd:2.4
COPY docker-bind.conf /usr/local/apache2/conf/extra/docker-bind.conf
COPY src/ /usr/local/apache2/htdocs/
RUN sed -i \
  -e 's/^#LoadModule proxy_module/LoadModule proxy_module/' \
  -e 's/^#LoadModule proxy_fcgi_module/LoadModule proxy_fcgi_module/' \
  /usr/local/apache2/conf/httpd.conf \
  && echo "Include conf/extra/docker-bind.conf" >> /usr/local/apache2/conf/httpd.conf
