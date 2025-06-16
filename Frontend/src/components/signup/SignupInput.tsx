import { useState } from "react";

const SignupInput = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [name, setName] = useState("");
  const [companyName, setCompanyName] = useState("");
  const [businessStatus, setBusinessStatus] = useState("");
  const [businessCategory, setBusinessCategory] = useState("");
  const [businessNumber, setBusinessNumber] = useState("");
  const [address, setAddress] = useState("");

  const handleButtonClick = () => {};

  return (
    <div className="flex flex-col space-y-10">
      <div className="flex flex-col space-y-4">
        <h2>사용하실 이메일과 비밀번호를 입력해주세요!</h2>
        {/* 이메일 */}
        <div className="flex items-center">
          <p>이메일을 입력해주세요.</p>
          <input
            className="border px-2 py-1 rounded"
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
          />
        </div>

        {/* 비밀번호 */}
        <div className="flex items-center">
          <p>비밀번호를 입력해주세요.</p>
          <input
            className="border px-2 py-1 rounded"
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
          />
        </div>
      </div>

      <div className="flex flex-col space-y-4">
        <h2>회사의 정보를 입력해주세요!</h2>
        {/* 이름 */}
        <div className="flex items-center">
          <p>이름을 입력해주세요.</p>
          <input
            className="border px-2 py-1 rounded"
            type="text"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </div>

        {/* 회사 이름 */}
        <div className="flex items-center">
          <p>회사명을 입력해주세요.</p>
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
            <p>업태</p>
            <input
              className="border px-2 py-1 rounded"
              type="text"
              value={businessStatus}
              onChange={(e) => setBusinessStatus(e.target.value)}
            />
          </div>
          {/* 종목 */}
          <div className="flex items-center">
            <p>종목</p>
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
          <p>사업자번호를 입력해주세요.</p>
          <input
            className="border px-2 py-1 rounded"
            type="text"
            value={businessNumber}
            onChange={(e) => setBusinessNumber(e.target.value)}
          />
        </div>

        {/* 주소 */}
        <div className="flex items-center">
          <p>주소를 입력해주세요.</p>
          <input
            className="border px-2 py-1 rounded"
            type="text"
            value={address}
            onChange={(e) => setAddress(e.target.value)}
          />
        </div>
      </div>
      <button onClick={handleButtonClick}>회원가입</button>
    </div>
  );
};

export default SignupInput;
