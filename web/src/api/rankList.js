import request from "@/utils/http";

export function getRankList(params) {
  return request({
    url: "/rankList/toplist",
    method: "get",
    params,
  });
}
