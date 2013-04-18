title: Quantitative Research
shortcut: DOC:Quantitative Research
---
This page contains various quantitative research papers that have been written by the OpenGamma quantitative development team.




`Adjoint Algorithmic Differentiation: Calibration and Implicit Function Theorem <http://developers.opengamma.com/quantitative-research/Adjoint-Algorithmic-Differentiation-OpenGamma.pdf>`_  - Adjoint Algorithmic Differentiation is an efficient way to obtain financial instrument price derivatives with respect to the data inputs. Often the differentiation does not cover the full pricing process when a model calibration is performed. Thanks to the implicit function theorem, the differentiation of the solver embedded in the calibration is not required to differentiate the full pricing process. An efficient approach to the full differentiation process is described.

`Algorithmic Differentiation in Finance: Root Finding and Least Square Calibration <http://developers.opengamma.com/quantitative-research/Algorithmic-Differentiation-in-Finance-Root-Finding-and-Least-Square-Calibration-OpenGamma.pdf>`_  \- Algorithmic Differentiation is an efficient way to compute derivatives of a value with respect to the data inputs. In finance the model calibration to market data can be an important part of the valuation process. In presence of calibration, when obtained through exact equation solving or optimisation, very efficient implementation can be done using the implicit function theorem with the standard AD approach. Previous results discussing the exact case are here extended to the case of calibration obtained by a least-square approach.

`Deliverable Interest Rate Swap Futures: Pricing in Gaussian HJM Model <http://developers.opengamma.com/quantitative-research/Deliverable-Interest-Rate-Swap-Futures-Pricing-in-Gaussian-HJM-Model-OpenGamma.pdf>`_  - CME will soon be proposing a new product: Deliverable Interest Rate Swap Futures. This note analyses the pricing of such futures in the Gaussian multi-factor HJM model and multi-curves framework. We also provide numerical examples of prices and hedging with those futures.

`Equity Variance Swap with Dividends <http://developers.opengamma.com/quantitative-research/Equity-Variance-Swaps-with-Dividends-OpenGamma.pdf>`_  - We present a discussion paper on how to price equity variance swaps in the presence of known (cash and proportional) dividends.

`Interest Rate Instruments and Market Conventions Guide <http://www.opengamma.com/downloads/interest-rate-instruments-and-market-conventions-guide.pdf>`_  - Conventions and market standards for the most common interest rate instruments.

`Local Volatility <http://developers.opengamma.com/quantitative-research/Local-Volatility-OpenGamma.pdf>`_  - We present details of computing a local volatility surface from market data, then numerically solving different PDE representations to reproduce the market prices, and compute greeks.

`Multi-Curves: Variations on a Theme <http://developers.opengamma.com/quantitative-research/Multi-Curves-Variations-on-a-Theme-OpenGamma.pdf>`_  \- The multi-curves framework is often implemented in a way to recycle one-curve formulas; there are no fundamental reasons behind the choice. Here we present different approaches to the multi\- curves framework. They vary by the choice of building blocks instruments (Ibor coupon or futures) and the definition of curve (pseudo-discount factors or direct forward rate). The features of the different approaches are described.

`Multi-Curves Framework with Stochastic Spread: A Coherent Approach to STIR Futures and Their Options <http://developers.opengamma.com/quantitative-research/Multi-Curves-Stochastic-Spread-STIR-Futures-Options-OpenGamma.pdf>`_  \- The development of the multi-curves framework has mainly concentrated on swaps and related products. By opposition, this contribution focuses on STIR futures and their options. They are analysed in a stochastic multiplicative spread multi-curves framework which allows a simultaneous modelling of the Ibor rates and of the cash-account required for futures with continuous margining. The framework proposes a coherent pricing of cap/floor, futures and options on futures.
