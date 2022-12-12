import axios from "axios";
import { getToken } from "./token";
import { store } from "@/store";

axios.defaults.headers["Content-Type"] = "application/json;charset=utf-8";

const errorCode = {
  401: "认证失败，无法访问系统资源",
  403: "当前操作没有权限",
  404: "访问资源不存在",
  default: "系统未知错误，请反馈给管理员",
};

const rcc = {
  SUCCESS: 200,
  ERROR: 500,
  EXPIRE: 403,
};

const service = axios.create({
  baseURL: "http://127.0.0.1:8080/",
  timeout: 10000,
});

service.interceptors.request.use(
  (config) => {
    // 是否需要设置 token
    const isToken = (config.headers || {}).isToken === false;
    if (getToken() && !isToken) {
      config.headers["Authorization"] = "Bearer " + getToken(); // 让每个请求携带自定义token 请根据实际情况自行修改
    }
    return config;
  },
  (error) => {
    Promise.reject(error);
  }
);

service.interceptors.response.use(
  (res) => {
    const code = res.data.status || rcc.SUCCESS;
    const msg = errorCode[code] || res.data.message || errorCode["default"];
    if (
      res.request.responseType === "blob" ||
      res.request.responseType === "arraybuffer"
    ) {
      return res.data;
    }

    if (code === 401 || res.status === 401) {
      return Promise.reject("无效的会话，或者会话已过期，请重新登录。");
    } else if (code === 500) {
      return Promise.reject(new Error(msg));
    } else {
      return Promise.resolve({ ...res.data, code: code });
    }
  },
  (error) => {
    let { message } = error;
    if (message == "Network Error") {
      message = "后端接口连接异常";
    } else if (message.includes("timeout")) {
      message = "系统接口请求超时";
    } else if (message.includes("Request failed with status code")) {
      message = "系统接口" + message.substr(message.length - 3) + "异常";
    }
    console.log(message);
    return Promise.reject(error.response.data);
  }
);

//#region 基本请求快捷使用
/** get */
export function GET(url, config = {}) {
  return service.get(url, config);
}

/** post */
export function POST(url, data = {}, config = {}) {
  return service.post(url, data, config);
}

/** put */
export function PUT(url, data = {}, config = {}) {
  return service.put(url, data, config);
}

/** delete */
export function DELETE(url, config = {}) {
  return service.delete(url, config);
}
//#endregion

export default service;
