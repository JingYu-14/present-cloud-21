<?php

/**
 * @Author: wujinhan
 * @Date:   2020-03-29 19:53:56
 * @Last Modified by:   wujinhan
 * @Last Modified time: 2020-05-23 07:45:13
 */
namespace app\api\controller;

use think\Controller;
use think\Db;
use think\Exception;
use app\exception\TokenException;

class Base extends Controller
{
    protected function returnMsg($data, $msg, $status)
    {
    	$meta = ["msg" => $msg,"status" => $status];
    	return json(["data" => $data, "meta" => $meta]);
    }

    protected function checkToken()
    {
    	// $token=$_SERVER['authorization'];
    	var_dump($_SERVER);exit;
    	// var_dump($_SERVER['HTTP_AUTHORIZATION']);exit;
    	$expire=Db::table('user')->where('token',$token)->value('expire');
       	if(!$expire)
       	{
       		throw new TokenException(['data'=>[],'msg'=>'token不存在','status'=>401]);
       		// return $this->returnMsg([],'token不存在',401);
       	}
        if (time()-$expire>0) 
        {
        	throw new TokenException(['data'=>[],'msg'=>'token已过期','status'=>200]);
            // return $this->returnMsg([],'token已过期',200);
        }
        Db::table('user')->where('token',$token)->update(['expire'=>strtotime('+7 days')]);
    }
}