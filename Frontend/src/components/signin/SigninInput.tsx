import { useState } from "react";
import { signin } from "../../apis/auth";
import { useNavigate } from "react-router-dom";
import { setUsername } from "../../context/user";

const SigninInput = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate();

  const validateSignin = () => {
    if (!email || !password) {
      alert("모든 항목을 입력해주세요!");
      return false;
    }
    return true;
  };

  const handleButtonClick = async () => {
    if (!validateSignin()) return;

    try {
      const response = await signin({
        email,
        password,
      });
      console.log("로그인 성공", response);
      console.log(response.username);

      setUsername(response.username);
      navigate("/");
    } catch (error) {
      console.error("로그인 실패:", error);
    }
  };

  return (
    <div className="flex flex-col space-y-4">
      <h1>Sign In</h1>
      {/* 이메일 */}
      <div className="flex items-center">
        <p className="w-[14vw]">이메일</p>
        <input
          className="border px-2 py-1"
          type="email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />
      </div>

      {/* 비밀번호 */}
      <div className="flex items-center">
        <p className="w-[14vw]">비밀번호</p>
        <input
          className="border px-2 py-1"
          type="password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
      </div>
      <button className="mt-[2rem] h-[3rem]" onClick={handleButtonClick}>
        로그인
      </button>
    </div>
  );
};

export default SigninInput;
