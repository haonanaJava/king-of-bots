const TOKEN_KEY = "ADMIN-TOKEN";

export function getToken() {
  return localStorage.getItem(TOKEN_KEY);
}

export function setToken(jwt) {
  localStorage.setItem(TOKEN_KEY, jwt);
}

export function removeToken() {
  localStorage.removeItem(TOKEN_KEY);
}

export function saveUser(user) {
  localStorage.setItem("user", JSON.stringify(user));
}

export function getUser() {
  return JSON.parse(localStorage.getItem("user"));
}

export function removeUser() {
  localStorage.removeItem("user");
}
