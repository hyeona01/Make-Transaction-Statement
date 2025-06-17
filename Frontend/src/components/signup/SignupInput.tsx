import { useState } from "react";
import { signup } from "../../apis/auth";
import { useNavigate } from "react-router-dom";

const SignupInput = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [username, setUserame] = useState("");
  const [companyName, setCompanyName] = useState("");
  const [businessStatus, setBusinessStatus] = useState("");
  const [businessCategory, setBusinessCategory] = useState("");
  const [businessNumber, setBusinessNumber] = useState("");
  const [address, setAddress] = useState("");

  const navigate = useNavigate();

  const validateSignup = () => {
    if (
      !email ||
      !password ||
      !username ||
      !companyName ||
      !businessStatus ||
      !businessCategory ||
      !businessNumber ||
      !address
    ) {
      alert("모든 항목을 입력해주세요!");
      return false;
    }
    return true;
  };

  const handleButtonClick = async () => {
    if (!validateSignup()) return;

    try {
      const response = await signup({
        username,
        password,
        companyName,
        businessStatus,
        businessCategory,
        email,
        businessNumber,
        address,
      });
      console.log("회원가입 성공:", response);
      alert("환영합니다!");
      navigate("/signin");
    } catch (error) {
      console.error("회원가입 실패:", error);
    }
  };

  return (
    <div className="flex flex-col space-y-10">
      <div className="flex flex-col space-y-4">
        <h1>Sign up</h1>
        <h4>사용하실 이메일과 비밀번호를 입력해주세요!</h4>
        {/* 이메일 */}
        <div className="flex items-center">
          <p className="w-[14vw]">이메일</p>
          <input
            className="border px-2 py-1 rounded"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        {/* 비밀번호 */}
        <div className="flex items-center">
          <p className="w-[14vw]">비밀번호</p>
          <input
            className="border px-2 py-1 rounded"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
      </div>

      <div className="flex flex-col space-y-4">
        <h4>회사의 정보를 입력해주세요!</h4>
        {/* 이름 */}
        <div className="flex items-center">
          <p className="w-[14vw]">이름</p>
          <input
            className="border px-2 py-1 rounded"
            type="text"
            value={username}
            onChange={(e) => setUserame(e.target.value)}
          />
        </div>

        {/* 회사 이름 */}
        <div className="flex items-center">
          <p className="w-[14vw]">회사명</p>
          <input
            className="border px-2 py-1 rounded"
            type="text"
            value={companyName}
            onChange={(e) => setCompanyName(e.target.value)}
          />
        </div>

        {/* 업태 */}
        <div>
          <div className="flex items-center">
            <p className="w-[14vw]">업태</p>
            <input
              className="border px-2 py-1 rounded"
              type="text"
              value={businessStatus}
              onChange={(e) => setBusinessStatus(e.target.value)}
            />
          </div>
          {/* 종목 */}
          <div className="flex items-center">
            <p className="w-[14vw]">종목</p>
            <input
              className="border px-2 py-1 rounded"
              type="text"
              value={businessCategory}
              onChange={(e) => setBusinessCategory(e.target.value)}
            />
          </div>
        </div>

        {/* 사업자등록번호 */}
        <div className="flex items-center">
          <p className="w-[14vw]">사업자번호</p>
          <input
            className="border px-2 py-1 rounded"
            type="text"
            value={businessNumber}
            onChange={(e) => setBusinessNumber(e.target.value)}
          />
        </div>

        {/* 주소 */}
        <div className="flex items-center">
          <p className="w-[14vw]">주소</p>
          <input
            className="border px-2 py-1 rounded"
            type="text"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
        </div>
      </div>
      <button className="mt-[2rem] h-[3rem]" onClick={handleButtonClick}>
        회원가입
      </button>
    </div>
  );
};

export default SignupInput;
