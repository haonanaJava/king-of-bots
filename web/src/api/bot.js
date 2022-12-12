import request from "@/utils/http";

export function getBotList(params) {
  return request({
    url: "bot/list",
    method: "get",
    params,
  });
}

export function getBot(id) {
  return request({
    url: "bot/" + id,
    method: "get",
  });
}

export function addBot(data) {
  return request({
    url: "bot/add",
    method: "post",
    data,
  });
}

export function updateBot(data) {
  return request({
    url: "bot/update",
    method: "put",
    data,
  });
}

export function removeBot(id) {
  return request({
    url: "bot/remove/" + id,
    method: "delete",
  });
}
