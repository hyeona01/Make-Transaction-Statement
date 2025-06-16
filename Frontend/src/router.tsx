import { createBrowserRouter } from "react-router-dom";
import App from "./App";
import HomePage from "./pages/HomePage";
import SigninPage from "./pages/SigninPage";
import { SignupPage } from "./pages/SignupPage";
import SheetPage from "./pages/SheetPage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />,
    children: [
      { path: "/", element: <HomePage /> },
      { path: "/signin", element: <SigninPage /> },
      { path: "/signup", element: <SignupPage /> },
      { path: "/sheet", element: <SheetPage /> },
    ],
  },
]);

export default router;
