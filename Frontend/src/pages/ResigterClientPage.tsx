import { useState } from "react";
import { useNavigate } from "react-router-dom";
import type { clientRequest } from "../types/client";
import { postClient } from "../apis/client";

const ResigterClientPage = () => {
  const [clientData, setClientData] = useState<clientRequest>({
    name: "",
    representativeName: "",
    businessNumber: "",
    address: "",
    email: "",
    tel: "",
  });
  const navigate = useNavigate();

  const gotoSheet = () => {
    navigate("/sheet");
  };

  const handleButtonClick = async () => {
    try {
      if (!clientData) {
        console.error("거래처 정보가 없습니다.");
        return;
      }
      await postClient(clientData);
      alert("등록이 완료되었습니다.");
      window.location.reload();
    } catch (error) {
      console.error("거래처 등록 실패", error);
    }
  };

  return (
    <div>
      <h1>거래처 등록하기</h1>
      <div onClick={gotoSheet} className="mb-[2rem] underline cursor-pointer">
        거래명세서 발행하러가기
      </div>

      <div className="flex flex-col w-[50vw] items-center justify-center">
        <div className="flex items-center">
          <p className="w-[14vw]">거래처명</p>
          <input
            className="border px-2 py-1"
            type="text"
            value={clientData?.name}
            onChange={(e) =>
              setClientData({ ...clientData, name: e.target.value })
            }
          />
        </div>

        <div className="flex items-center">
          <p className="w-[14vw]">대표명</p>
          <input
            className="border px-2 py-1"
            type="text"
            value={clientData?.representativeName}
            onChange={(e) =>
              setClientData({
                ...clientData,
                representativeName: e.target.value,
              })
            }
          />
        </div>

        <div className="flex items-center">
          <p className="w-[14vw]">사업자등록번호</p>
          <input
            className="border px-2 py-1"
            type="text"
            value={clientData?.businessNumber}
            onChange={(e) =>
              setClientData({
                ...clientData,
                businessNumber: e.target.value,
              })
            }
          />
        </div>

        <div className="flex items-center">
          <p className="w-[14vw]">주소</p>
          <input
            className="border px-2 py-1"
            type="text"
            value={clientData?.address}
            onChange={(e) =>
              setClientData({
                ...clientData,
                address: e.target.value,
              })
            }
          />
        </div>

        <div className="flex items-center">
          <p className="w-[14vw]">이메일</p>
          <input
            className="border px-2 py-1"
            type="email"
            value={clientData?.email}
            onChange={(e) =>
              setClientData({
                ...clientData,
                email: e.target.value,
              })
            }
          />
        </div>

        <div className="flex items-center">
          <p className="w-[14vw]">전화번호</p>
          <input
            className="border px-2 py-1"
            type="tel"
            value={clientData?.tel}
            onChange={(e) =>
              setClientData({
                ...clientData,
                tel: e.target.value,
              })
            }
          />
        </div>
        <button
          className="mt-[2rem] h-[2rem] w-[12rem]"
          onClick={handleButtonClick}
        >
          등록
        </button>
      </div>
    </div>
  );
};

export default ResigterClientPage;
