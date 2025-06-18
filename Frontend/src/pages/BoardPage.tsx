import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import type { boardResponse } from "../types/board";
import { getAllBoardData } from "../apis/board";

const BoardPage = () => {
  const [posts, setPosts] = useState<boardResponse[]>([]);
  const navigate = useNavigate();

  const getPost = async () => {
    const response = await getAllBoardData();
    setPosts(response);
  };

  useEffect(() => {
    getPost();
  }, []);

  const handleButtonClick = () => {
    navigate("/board/post");
  };

  const handlePostClick = (id: number) => {
    navigate(`/board/${id}`);
  };

  return (
    <div>
      <h1>게시판</h1>
      <div className="w-full flex justify-end mb-[14px]">
        <button onClick={handleButtonClick}>작성하기</button>
      </div>
      <div className="flex flex-col gap-[14px]">
        {posts.map((post) => (
          <div
            key={post.id}
            className="border px-[8px] py-[12px]"
            onClick={() => handlePostClick(post.id)}
          >
            <div className="text-[1.5rem]">{post.title}</div>
            <div className="text-[1rem] mt-[10px]">{post.contents}</div>
            <div className="text-[0.75rem] flex justify-end">
              {post.writer} | {post.createdDate.slice(0, 10)}
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default BoardPage;
