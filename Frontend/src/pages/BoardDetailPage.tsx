import { useEffect, useState } from "react";
import type { boardResponse } from "../types/board";
import { getBoardData } from "../apis/board";
import { useParams } from "react-router-dom";

const BoardDetailPage = () => {
  const [board, setBoard] = useState<boardResponse>();
  const { id } = useParams();

  useEffect(() => {
    const fetchData = async () => {
      const response = await getBoardData(Number(id));
      setBoard(response);
    };

    fetchData();
  }, []);

  return (
    <div>
      <h1>게시판</h1>
      {board ? (
        <>
          <div className="mt-[3rem] mb-[2rem]">
            <div className="text-[1.5rem]">제목 : {board.title}</div>
          </div>
          <div className="mb-[2rem]">
            <div className="text-[1.5rem]">내용</div>
            <div className="text-[1rem] mt-[10px] border h-[40vh] p-[10px]">
              {board.contents}
            </div>
          </div>
        </>
      ) : (
        <div>불러오는 중</div>
      )}
    </div>
  );
};

export default BoardDetailPage;
