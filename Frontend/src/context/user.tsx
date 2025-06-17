export const setUsername = (name: string | null) => {
  localStorage.setItem("username", name || "");
};

export const getUsername = () => {
  return localStorage.getItem("username");
};

export const deleteUsername = () => {
  localStorage.removeItem("username");
};
