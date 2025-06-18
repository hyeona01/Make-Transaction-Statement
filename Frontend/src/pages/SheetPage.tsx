import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import type { clientResponse } from "../types/client";
import { getAllClient, getClient } from "../apis/client";
import type { excelRequest, sheetItem } from "../types/sheet";
import { generateExcel } from "../apis/sheet";

type DayEntry = {
  date: string;
  item: string;
  amount: number;
};

const SheetPage = () => {
  const [clients, setClients] = useState<clientResponse[]>([]);
  const [selectedClientId, setSelectedClientId] = useState<number | null>(null);
  const [selectedClient, setSelectedClient] = useState<clientResponse | null>(
    null
  );
  const [month, setMonth] = useState(""); // YYYY-MM
  const [entries, setEntries] = useState<DayEntry[]>([]);
  const [issueDate, setIssueDate] = useState<string>(
    new Date().toISOString().split("T")[0]
  );
  const [isLoading, setIsLoading] = useState<boolean>(false);

  const navigate = useNavigate();

  const gotoRegister = () => {
    navigate("/register-client");
  };

  const handleSelect = async (id: number) => {
    setSelectedClientId(id);
    try {
      const clientData = await getClient(id);
      setSelectedClient(clientData);
    } catch (error) {
      console.error("Error fetching client details:", error);
    }
  };

  const generateDates = (month: string) => {
    const [year, monthStr] = month.split("-");
    const daysInMonth = new Date(+year, +monthStr, 0).getDate();

    const newEntries: DayEntry[] = Array.from(
      { length: daysInMonth },
      (_, i) => {
        const day = String(i + 1).padStart(2, "0");
        return {
          date: `${month}-${day}`,
          item: "",
          amount: 0,
        };
      }
    );

    setEntries(newEntries);
  };

  const handleChange = (
    index: number,
    field: keyof DayEntry,
    value: string
  ) => {
    const updated = [...entries];
    updated[index] = {
      ...updated[index],
      [field]: field === "amount" ? Number(value) : value,
    };
    setEntries(updated);
  };

  const handleSubmit = async () => {
    if (!selectedClient) {
      alert("거래처를 선택해주세요.");
      return;
    }

    if (entries.length === 0) {
      alert("항목을 입력해주세요.");
      return;
    }

    try {
      setIsLoading(true);

      // 유효한 항목만 필터링 (항목명이 있고 금액이 0보다 큰 항목)
      const validEntries = entries.filter(
        (entry) => entry.item.trim() !== "" && entry.amount > 0
      );

      if (validEntries.length === 0) {
        alert("유효한 항목이 없습니다. 항목명과 금액을 입력해주세요.");
        setIsLoading(false);
        return;
      }

      // 총액 계산
      const totalAmount = validEntries.reduce(
        (sum, entry) => sum + entry.amount,
        0
      );
      // 세액 계산 (10%)
      const taxAmount = Math.round(totalAmount * 0.1);

      // Excel 데이터 생성
      const excelItems: sheetItem[] = validEntries.map((entry) => ({
        name: entry.item,
        specification: "",
        quantity: 1,
        unitPrice: entry.amount,
        amount: entry.amount,
        remark: entry.date,
      }));

      const excelData: excelRequest = {
        transactionDate: issueDate,
        clientName: selectedClient.name,
        businessNumber: selectedClient.businessNumber || "",
        representativeName: selectedClient.representativeName || "",
        totalAmount: totalAmount,
        taxAmount: taxAmount,
        items: excelItems,
      };

      // Excel 파일 생성 및 다운로드
      const blob = await generateExcel(excelData);
      const url = window.URL.createObjectURL(blob);
      const a = document.createElement("a");
      a.href = url;
      a.download = `거래명세서_${selectedClient.name}_${month}.xlsx`;
      document.body.appendChild(a);
      a.click();
      window.URL.revokeObjectURL(url);
      document.body.removeChild(a);

      alert("거래명세서가 생성되었습니다.");
    } catch (error) {
      console.error("Error generating excel:", error);
      alert("거래명세서 생성 중 오류가 발생했습니다.");
    } finally {
      setIsLoading(false);
    }
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await getAllClient();
        setClients(response);
      } catch (error) {
        console.error("Error fetching clients:", error);
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      <h1>거래명세서 발행하기</h1>
      <div
        onClick={gotoRegister}
        className="mb-[2rem] underline cursor-pointer"
      >
        거래처 등록 먼저 하기
      </div>
      <h2>거래처 선택</h2>
      <table className="w-full border-collapse">
        <thead>
          <tr className="font-bold border-b border-gray-300">
            <th className="text-left py-2">대표자명</th>
            <th className="text-left py-2">회사명</th>
            <th className="text-left py-2">주소</th>
          </tr>
        </thead>
        <tbody>
          {clients.map((client) => (
            <tr
              key={client.id}
              onClick={() => handleSelect(client.id)}
              className={`cursor-pointer ${
                selectedClientId === client.id ? "bg-[lightyellow]" : ""
              }`}
            >
              <td className="py-2">{client.representativeName}</td>
              <td className="py-2">{client.name}</td>
              <td className="py-2">{client.address}</td>
            </tr>
          ))}
        </tbody>
      </table>

      <h2>발행일</h2>
      <input
        type="date"
        value={issueDate}
        onChange={(e) => setIssueDate(e.target.value)}
        className="border px-2 py-1 mt-2 mb-4"
      />

      <h2>발행 월 선택</h2>
      <input
        type="month"
        value={month}
        onChange={(e) => {
          setMonth(e.target.value);
          generateDates(e.target.value);
        }}
        className="border px-2 py-1 mt-2 mb-4"
      />

      {entries.length > 0 && (
        <>
          <h2>항목 입력</h2>
          <table className="w-full border-collapse mt-2">
            <thead>
              <tr className="border-b border-gray-300">
                <th className="text-left py-2">날짜</th>
                <th className="text-left py-2">항목</th>
                <th className="text-left py-2">금액</th>
              </tr>
            </thead>
            <tbody>
              {entries.map((entry, idx) => (
                <tr key={entry.date}>
                  <td className="py-1">{entry.date}</td>
                  <td className="py-1">
                    <input
                      type="text"
                      value={entry.item}
                      onChange={(e) =>
                        handleChange(idx, "item", e.target.value)
                      }
                      className="border px-2 py-1 w-full"
                    />
                  </td>
                  <td className="py-1">
                    <input
                      type="number"
                      value={entry.amount}
                      onChange={(e) =>
                        handleChange(idx, "amount", e.target.value)
                      }
                      className="border px-2 py-1 w-full"
                    />
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </>
      )}
      <button
        onClick={handleSubmit}
        className="w-full h-[2rem] mt-[2rem] bg-blue-500 text-white font-bold py-2 px-4 rounded disabled:bg-gray-400"
        disabled={isLoading || !selectedClient || entries.length === 0}
      >
        {isLoading ? "처리 중..." : "발행하기"}
      </button>
    </div>
  );
};

export default SheetPage;
