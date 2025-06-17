import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import HomePage from "./pages/HomePage";
import SigninPage from "./pages/SigninPage";
import { SignupPage } from "./pages/SignupPage";
import SheetPage from "./pages/SheetPage";
import BoardPage from "./pages/BoardPage";
import PostBoardPage from "./pages/PostBoardPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      { path: "/", element: <HomePage /> },
      { path: "/signin", element: <SigninPage /> },
      { path: "/signup", element: <SignupPage /> },
      { path: "/sheet", element: <SheetPage /> },
      { path: "/board", element: <BoardPage /> },
      { path: "/board/post", element: <PostBoardPage /> },
    ],
  },
]);

export default router;
