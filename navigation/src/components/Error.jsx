import { useRouteError } from "react-router-dom";

export default function Error() {
  const error = useRouteError();
  console.log(error);   // check the console to see the full contents of the error object

  return (
    <div>
      <h1>Error.</h1>
      <p>{error.data}</p>
    </div>
  );
}