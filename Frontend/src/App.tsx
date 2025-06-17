import { Outlet } from "react-router-dom";

const Layout = () => {
  return (
    <div className="w-screen min-h-screen bg-white flex justify-center items-center">
      <div className="w-full max-w-[1080px] min-h-screen flex flex-col">
        <Outlet />
      </div>
    </div>
  );
};

function App() {
  return <Layout />;
}

export default App;
