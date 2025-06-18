import { useState } from "react";
import { postBoard } from "../../apis/board";
import { useNavigate } from "react-router-dom";

const BoardInput = () => {
  const [title, setTitle] = useState("");
  const [contents, setContents] = useState("");

  const navigate = useNavigate();

  const handleSubmit = async () => {
    if (title == "") {
      alert("제목을 입력해주세요!");
      return;
    }
    if (contents == "") {
      alert("내용을 입력해주세요!");
      return;
    }

    try {
      const response = await postBoard({
        title,
        contents,
      });
      console.log("게시글 작성 성공", response);
      navigate("/board");
    } catch (error) {
      console.error("게시글 작성 실패:", error);
    }
  };

  return (
    <div>
      <div className="mt-[3rem] mb-[2rem]">
        <div className="text-[1.5rem] mb-[10px]">제목</div>
        <input
          type="text"
          className="w-full p-[10px]"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          placeholder="제목을 입력하세요"
        />
      </div>
      <div className="mb-[2rem]">
        <div className="text-[1.5rem] mb-[10px]">내용</div>
        <textarea
          className="w-full h-[50vh] p-[10px]"
          value={contents}
          onChange={(e) => setContents(e.target.value)}
          placeholder="내용을 입력하세요"
        />
      </div>
      <button className="w-full h-[2rem]" onClick={handleSubmit}>
        제출
      </button>
    </div>
  );
};

export default BoardInput;
