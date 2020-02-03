# -*- coding: utf-8 -*-
import os,sys
import redis
def initializer(context):
    conn_pool=redis.ConnectionPool(host=os.environ['REDIS_HOST'],password=os.environ['REDIS_PASSWORD'],port=os.environ['REDIS_PORT'],decode_responses=True)

def handler(event, context):
    r = redis.Redis(connection_pool=conn_pool)
    r.set('test','89898')
    r.set('zyh_info','{"name":"Tanya","password":"123456","account":11234}')
    print(r.get('test'))
    return r.get('zyh_info')