######################################################
## build the container
##  docker build  --rm=true -t elasticsearch:2.1.2 .
######################################################


######################################################
## run the built container
## docker run -i -t  elasticsearch:2.1.2 /bin/bash
## docker run -i -t -v  /c/Users/ZCadi/docker/es/2.1.2/data-mononode:/data  -p 8200:8200 -p 9300:9300 elasticsearch:2.1.2
## docker run -i -t -v  /c/Users/ZCadi/docker/es/2.1.2/data-mononode:/data  -p 8200:8200 -p 9300:9300 elasticsearch:2.1.2
## docker run  -t -v  /c/Users/ZCadi/docker/es/2.1.2/data-mononode:/data  -p 8200:8200 -p 9300:9300 --name elasticsearch elasticsearch:2.1.2
######################################################


FROM java:8-jre

# grab gosu for easy step-down from root
RUN gpg --keyserver ha.pool.sks-keyservers.net --recv-keys B42F6819007F00F88E364FD4036A9C25BF357DD4
RUN arch="$(dpkg --print-architecture)" \
	&& set -x \
	&& curl -o /usr/local/bin/gosu -fSL "https://github.com/tianon/gosu/releases/download/1.3/gosu-$arch" \
	&& curl -o /usr/local/bin/gosu.asc -fSL "https://github.com/tianon/gosu/releases/download/1.3/gosu-$arch.asc" \
	&& gpg --verify /usr/local/bin/gosu.asc \
	&& rm /usr/local/bin/gosu.asc \
	&& chmod +x /usr/local/bin/gosu

# https://www.elastic.co/guide/en/elasticsearch/reference/current/setup-repositories.html
# https://packages.elasticsearch.org/GPG-KEY-elasticsearch
RUN apt-key adv --keyserver ha.pool.sks-keyservers.net --recv-keys 46095ACC8548582C1A2699A9D27D666CD88E42B4

ENV ELASTICSEARCH_MAJOR 2.1
ENV ELASTICSEARCH_VERSION 2.1.2
ENV ELASTICSEARCH_REPO_BASE http://packages.elasticsearch.org/elasticsearch/2.x/debian

RUN echo "deb $ELASTICSEARCH_REPO_BASE stable main" > /etc/apt/sources.list.d/elasticsearch.list

RUN set -x \
	&& apt-get update \
	&& apt-get install -y --no-install-recommends elasticsearch=$ELASTICSEARCH_VERSION \
	&& rm -rf /var/lib/apt/lists/*

ENV PATH /usr/share/elasticsearch/bin:$PATH

RUN set -ex \
	&& for path in \
		/usr/share/elasticsearch/data \
		/usr/share/elasticsearch/logs \
		/usr/share/elasticsearch/plugins \
		/usr/share/elasticsearch/config \
		/usr/share/elasticsearch/config/scripts \
	; do \
		mkdir -p "$path"; \
		chown -R elasticsearch:elasticsearch "$path"; \
	done

COPY config /usr/share/elasticsearch/config

VOLUME /usr/share/elasticsearch/data

COPY docker-entrypoint.sh /
RUN chown  elasticsearch:elasticsearch docker-entrypoint.sh 

EXPOSE 8200 9300
ENTRYPOINT ["/docker-entrypoint.sh"]
CMD ["elasticsearch"]
