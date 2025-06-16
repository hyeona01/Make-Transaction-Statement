import { useNavigate } from "react-router-dom";

const HomePage = () => {
  const navigate = useNavigate();

  const handleSigninClick = () => {
    navigate("/signin");
  };
  const handleSignupClick = () => {
    navigate("/signup");
  };
  const handleMakeSheetClick = () => {
    navigate("/sheet");
  };
  return (
    <div>
      <button onClick={handleSigninClick}>Sign In</button>
      <button onClick={handleSignupClick}>Sign Up</button>
      <button onClick={handleMakeSheetClick}>Make sheet</button>
    </div>
  );
};

export default HomePage;
