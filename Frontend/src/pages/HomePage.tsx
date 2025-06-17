import { useNavigate } from "react-router-dom";
import { deleteAccessToken, getAccessToken } from "../utils/auth";
import { deleteUsername, getUsername } from "../context/user";

const HomePage = () => {
  const navigate = useNavigate();
  const token = getAccessToken();
  const username = getUsername();

  const handleSigninClick = () => {
    navigate("/signin");
  };
  const handleSignupClick = () => {
    navigate("/signup");
  };
  const handleMakeSheetClick = () => {
    navigate("/sheet");
  };
  const handleBoardClick = () => {
    navigate("/board");
  };
  const handleLogoutClick = () => {
    deleteAccessToken();
    deleteUsername();
    window.location.reload();
  };

  return (
    <div style={{ gap: "10px", display: "flex", flexDirection: "column" }}>
      {token && username ? (
        <>
          <h1>{username}님 어서오세요 😁</h1>
          <button
            style={{ width: "30vw", height: "30px" }}
            onClick={handleMakeSheetClick}
          >
            Make sheet
          </button>
          <button
            style={{ width: "30vw", height: "30px" }}
            onClick={handleBoardClick}
          >
            board
          </button>
          <button
            style={{ width: "30vw", height: "30px" }}
            onClick={handleLogoutClick}
          >
            Logout
          </button>
        </>
      ) : (
        <>
          <h1>어서오세요 😁</h1>
          <h2>로그인 후에 서비스를 이용하세요!</h2>
          <button
            style={{ width: "30vw", height: "30px" }}
            onClick={handleSigninClick}
          >
            Sign In
          </button>
          <button
            style={{ width: "30vw", height: "30px" }}
            onClick={handleSignupClick}
          >
            Sign Up
          </button>
        </>
      )}
    </div>
  );
};

export default HomePage;
