import { useNavigate } from "react-router-dom";

const BoardPage = () => {
  const navigate = useNavigate();
  const handleButtonClick = () => {
    navigate("/board/post");
  };
  return (
    <div>
      <h1>게시판</h1>
      <button onClick={handleButtonClick}>작성하기</button>
    </div>
  );
};

export default BoardPage;
